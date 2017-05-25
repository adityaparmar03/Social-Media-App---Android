package cmpe277.sjsu.edu.teamproject.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;

import java.io.File;
import java.net.URL;
import java.util.Date;

import cmpe277.sjsu.edu.teamproject.R;

import static android.app.Activity.RESULT_OK;


public class CreatePostFragment extends Fragment {

    private static CreatePostFragment fragment;
    private static int resultcode = 1;
    String imgDecodableString;
    ImageView imageView;

    public static CreatePostFragment newInstance() {
        if(fragment == null)
            fragment = new CreatePostFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        setHasOptionsMenu(true);

        return view;





    }
    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == resultcode && resultCode == RESULT_OK && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
               // Toast.makeText(getActivity(),selectedImage.toString(),Toast.LENGTH_LONG).show();
                File file = new File(selectedImage.getPath());
                Toast.makeText(getActivity(),file.toString(),Toast.LENGTH_LONG).show();

                imageView = (ImageView) getActivity().findViewById(R.id.post_image_imageview);
                imageView.setImageURI(selectedImage);
                new File(selectedImage.getPath());
                uploadonS3(file);


            } else {
                Toast.makeText(getActivity(), "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem actionViewItem = menu.findItem(R.id.action_item);
        View v = MenuItemCompat.getActionView(actionViewItem);

        Button button = (Button) v.findViewById(R.id.done_btn);
        button.setVisibility(View.VISIBLE);
        button.setClickable(true);
        button.setText(getString(R.string.post));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), getString(R.string.post_success), Toast.LENGTH_SHORT).show();

                if (fragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.remove( fragment);
                    ft.commit();
                }
            }
        });

        TextView cancel = (TextView) v.findViewById(R.id.title);
        cancel.setText(getString(android.R.string.cancel));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.remove( fragment);
                    ft.commit();
                }
            }
        });

        TextView choosephoto = (TextView) getActivity().findViewById(R.id.choose_photos_textview);
        choosephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, resultcode);
            }
        });

        TextView uploadphoto = (TextView) getActivity().findViewById(R.id.photos_textview);
        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public Boolean uploadonS3(File file)
    {
        AmazonS3Client s3Client =   new AmazonS3Client( new BasicAWSCredentials( "AKIAIAP2EKQ5UPNOAANA","uAYDPPB5n4M0QQ6hFeMno0EYbLs2M2L00YUTS1qZ" ) );

        PutObjectRequest por =    new PutObjectRequest( "cmpe277", "imagename",  file  );

        por.setCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject( por );

        ResponseHeaderOverrides responseHeaderOverrides = new ResponseHeaderOverrides();
        responseHeaderOverrides.setContentType( "image/jpeg" );

        GeneratePresignedUrlRequest urlRequest =    new GeneratePresignedUrlRequest( "cmpe277","imagename");
        // Added an hour's worth of milliseconds to the current time.
        urlRequest.setExpiration(new Date( System.currentTimeMillis() + 3600000 ) );
        urlRequest.setResponseHeaders(responseHeaderOverrides);


        URL url = s3Client.generatePresignedUrl( urlRequest );



        String imagepaths3 = "https://s3-us-west-1.amazonaws.com/cmpefb"+url.getPath();

        Log.d("path:",imagepaths3);
        return true;

    }




}

// Ref : https://aws.amazon.com/articles/3002109349624271