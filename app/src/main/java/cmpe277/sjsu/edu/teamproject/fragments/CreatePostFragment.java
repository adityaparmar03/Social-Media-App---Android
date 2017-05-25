package cmpe277.sjsu.edu.teamproject.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.Services.TimelineFeedService;
import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.PostPostBody;
import cmpe277.sjsu.edu.teamproject.model.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;


public class CreatePostFragment extends Fragment {

    private static CreatePostFragment fragment;

    private static Uri selectedImage;
    private static String imgPath="";
    static int requestGallery = 100;

    ImageView mImageView;
    String s3ImageURL;

    public static CreatePostFragment getInstance() {
        if(fragment == null)
            fragment = new CreatePostFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setHasOptionsMenu(true);

        return view;

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
        getData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), getString(R.string.post_success), Toast.LENGTH_SHORT).show();
                setData();

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

        TextView txtgalary = (TextView) getActivity().findViewById(R.id.gallery_textview);
        txtgalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                if(gallery.resolveActivity(getActivity().getPackageManager())!=null) {
                    startActivityForResult(gallery,requestGallery);

                }
            }

        });

    }

    public String uploadOnS3(File file) {
        Random rand = new Random();
        int  randomNo = rand.nextInt(999999) + 1;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String s3Imagename = "POST" + timeStamp +randomNo;

        AmazonS3Client amazonS3Client =   new AmazonS3Client( new BasicAWSCredentials( "AKIAIAP2EKQ5UPNOAANA","uAYDPPB5n4M0QQ6hFeMno0EYbLs2M2L00YUTS1qZ" ) );
        PutObjectRequest putObjectRequest =    new PutObjectRequest( "cmpe277", s3Imagename ,  file  );
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3Client.putObject( putObjectRequest );
        ResponseHeaderOverrides responseHeaderOverrides = new ResponseHeaderOverrides();
        responseHeaderOverrides.setContentType( "image/jpeg" );
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest( "cmpe277",s3Imagename);
        urlRequest.setExpiration(new Date( System.currentTimeMillis() + 3600000 ) );
        urlRequest.setResponseHeaders(responseHeaderOverrides);


        URL url = amazonS3Client.generatePresignedUrl( urlRequest );
        s3ImageURL = getString(R.string.s3)+url.getPath();

        //Toast.makeText(getActivity(),s3ImageURL,Toast.LENGTH_LONG).show();
        return s3ImageURL;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==requestGallery && resultCode == RESULT_OK && data!=null) {

            selectedImage = data.getData();

            imgPath=getRealPathFromURI(selectedImage);
            File file = new File(imgPath);


            if(!uploadOnS3(file).equals(" ")){
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), selectedImage);
                    mImageView = (ImageView)getActivity().findViewById(R.id.post_image_imageview);
                    mImageView.setImageBitmap(bitmap);
                    mImageView.setMaxHeight(bitmap.getHeight());
                    mImageView.setMaxWidth(bitmap.getWidth());

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {

                Toast.makeText(getActivity(),getString(R.string.somethingwentwrong),Toast.LENGTH_LONG).show();
            }


        }

    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(index);

    }

    public void getData(){
        TextView screenname = (TextView)getActivity().findViewById(R.id.screen_name_text_view);
        screenname.setText("Aditya");
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.profile_pic_image_view);
        Glide.with(this)
                .load("https://s3-us-west-2.amazonaws.com/cmpe277/POST20170525_112442288506")
                .error(R.mipmap.ic_launcher)
                .into(imageView);


    }

    public void setData(){


        EditText content = (EditText) getActivity().findViewById(R.id.create_post_textview);

        PostPostBody postPostBody = new PostPostBody();
        postPostBody.setEmailId(Session.LoggedEmail);
        postPostBody.setContent(content.getText().toString());
        postPostBody.setMediaURL(s3ImageURL);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TimelineFeedService postservice = retrofit.create(TimelineFeedService.class);
        Call<GenericPostResponse> callpost = postservice.postPost(postPostBody);
        callpost.enqueue(new Callback<GenericPostResponse>() {
            @Override
            public void onResponse(Call<GenericPostResponse> call, Response<GenericPostResponse> response) {

                switch (response.body().getStatus()) {

                     case "200":
                         Toast.makeText(getActivity(), "Post Successfully.", Toast.LENGTH_LONG).show();
                         break;

                     default:
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                        break;
                }
            }


            @Override
            public void onFailure(Call<GenericPostResponse> call, Throwable t) {

            }
        });


    }


}

// Ref : https://aws.amazon.com/articles/3002109349624271