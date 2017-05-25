package cmpe277.sjsu.edu.teamproject.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.Services.ProfileService;
import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.UpdateProfile;
import cmpe277.sjsu.edu.teamproject.model.UserProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;


public class UpdateProfileFragment extends Fragment {

    private Context context;

    private static UpdateProfileFragment fragment;
    private static UserProfile userProfile;

    private static Uri selectedImage;
    private static String imgPath="";
    static int requestGallery = 100;

    ImageView mImageView;
    String s3ImageURL;


    public static UpdateProfileFragment getInstance(UserProfile userProfile) {
        if(fragment==null)
            fragment = new UpdateProfileFragment();
        UpdateProfileFragment.userProfile = userProfile;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.update_profile_fragment, container, false);
        setHasOptionsMenu(true);

        context = getActivity();

        init(view);

        return view;
    }

    private void init(View view) {

        TextView txtgalary = (TextView) view.findViewById(R.id.profile_photos_textview);
        txtgalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                if (gallery.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(gallery, requestGallery);

                }
            }

        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_item);
        View actionView = MenuItemCompat.getActionView(menuItem);

        Button button = (Button) actionView.findViewById(R.id.done_btn);
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);

        TextView textView = (TextView) actionView.findViewById(R.id.title);
        textView.setText(getString(R.string.update_profile));
    }

    private void setData()
    {
        EditText screenName = (EditText) getActivity().findViewById(R.id.tx_update_profile_screenname);
        EditText location = (EditText) getActivity().findViewById(R.id.tx_update_profile_location);
        EditText profession = (EditText) getActivity().findViewById(R.id.tx_update_profile_profession);
        EditText aboutme = (EditText) getActivity().findViewById(R.id.tx_update_profile_aboutme);
        EditText interest = (EditText) getActivity().findViewById(R.id.tx_update_profile_interest);

        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.setScreenName(screenName.getText().toString());
        updateProfile.setLocation(location.getText().toString());
        updateProfile.setProfession(profession.getText().toString());
        updateProfile.setAboutme(aboutme.getText().toString());
        updateProfile.setInterest(interest.getText().toString());
        updateProfile.setProfilepicUrl(s3ImageURL);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProfileService profileService = retrofit.create(ProfileService.class);

        Call<GenericPostResponse> callprofile = profileService.updateprofile(updateProfile);

        callprofile.enqueue(new Callback<GenericPostResponse>() {
            @Override
            public void onResponse(Call<GenericPostResponse> call, Response<GenericPostResponse> response) {

                switch (response.body().getStatus()) {

                    case "200":
                        Toast.makeText(getActivity(), "Account Created Succeessfully, Please LogIN", Toast.LENGTH_LONG).show();
                        break;

                    default:
                        Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_LONG).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<GenericPostResponse> call, Throwable t) {

                Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_LONG).show();
            }
        });


    }

    public String uploadOnS3(File file)
    {
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
            }
            else
            {
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


}
