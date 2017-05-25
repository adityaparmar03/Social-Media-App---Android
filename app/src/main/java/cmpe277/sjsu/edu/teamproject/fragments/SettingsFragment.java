package cmpe277.sjsu.edu.teamproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.Services.SettingsService;
import cmpe277.sjsu.edu.teamproject.model.CurrentUserSessionModel;
import cmpe277.sjsu.edu.teamproject.model.SettingsGetResponse;
import cmpe277.sjsu.edu.teamproject.model.SettingsPostRequest;
import cmpe277.sjsu.edu.teamproject.model.StatusForAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsFragment extends Fragment {

    private static SettingsFragment fragment;
    Switch aSwitchpublic;
    Switch aSwitchnotification;
    Boolean notificationState;
    Boolean publicstate;

    public static SettingsFragment getInstance() {
        if (fragment == null) {
            fragment = new SettingsFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;





    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button apply = (Button) getActivity().findViewById(R.id.btn_setting_apply);

        getdata();


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setdata();

            }
        });

    }

    public void getdata(){

        aSwitchpublic = (Switch) getActivity().findViewById(R.id.switch_settings_public);
        aSwitchnotification = (Switch)getActivity().findViewById(R.id.switch_settings_notification);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SettingsService settingsclient = retrofit.create(SettingsService.class);


        Call<SettingsGetResponse> callsettings = settingsclient.getallSettings(CurrentUserSessionModel.LoggedEmail);
        callsettings.enqueue(new Callback<SettingsGetResponse>() {
            @Override
            public void onResponse(Call<SettingsGetResponse> call, Response<SettingsGetResponse> response) {


                if(response.body().getStatus().equals("200"))
                {
                    aSwitchnotification.setChecked(response.body().getNotification());
                    aSwitchpublic.setChecked(response.body().getVisibility());

                }
                else
                {
                    Toast.makeText(getActivity(),"Something went wrong, please try again !",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<SettingsGetResponse> call, Throwable t) {

            }
        });


    }

    public void setdata(){

        aSwitchpublic = (Switch) getActivity().findViewById(R.id.switch_settings_public);
        aSwitchnotification = (Switch)getActivity().findViewById(R.id.switch_settings_notification);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SettingsPostRequest settingsPostRequest = new SettingsPostRequest();
        settingsPostRequest.setEmailid(CurrentUserSessionModel.LoggedEmail);
        settingsPostRequest.setVisibility(aSwitchpublic.isChecked());
        settingsPostRequest.setNotification(aSwitchnotification.isChecked());

        SettingsService settingsService = retrofit.create(SettingsService.class);
        Call<StatusForAll> callsettings = settingsService.postallSettings(settingsPostRequest);

        callsettings.enqueue(new Callback<StatusForAll>() {
            @Override
            public void onResponse(Call<StatusForAll> call, Response<StatusForAll> response) {
                if(response.body().getStatus().equals("200"))
                {
                    Toast.makeText(getActivity(),"Your settings are saved successfully.",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getActivity(),"Something went wrong, please try again !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<StatusForAll> call, Throwable t) {

            }
        });

    }

}
