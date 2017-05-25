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
import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.Session;
import cmpe277.sjsu.edu.teamproject.model.Settings;
import cmpe277.sjsu.edu.teamproject.model.SettingsPostBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsFragment extends Fragment {

    private Switch publicSwitch;
    private Switch notificationsSwitch;
    private Switch onlineSwitch;
    private Button applyButton;


    private static SettingsFragment fragment;

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
        publicSwitch = (Switch) view.findViewById(R.id.public_switch);
        notificationsSwitch = (Switch) view.findViewById(R.id.notifications_switch);
        onlineSwitch = (Switch) view.findViewById(R.id.online_switch);
        applyButton = (Button) view.findViewById(R.id.apply_button);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fetchUserSettings();

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateUserSettings();
            }
        });

    }

    private void fetchUserSettings() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SettingsService settingsService = retrofit.create(SettingsService.class);

        Call<Settings> callSettings = settingsService.getSettings(Session.LoggedEmail);

        callSettings.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {

                if (response.body().getStatus().equals("200")) {
                    notificationsSwitch.setChecked(response.body().getNotification());
                    publicSwitch.setChecked(response.body().getVisibility());
                    onlineSwitch.setChecked(response.body().getOnline());


                } else {

                    Toast.makeText(getActivity(),"Something went wrong, please try again !",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {

            }
        });

    }

    private void updateUserSettings() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SettingsPostBody settingsPostBody = new SettingsPostBody();
        settingsPostBody.setEmailid(Session.LoggedEmail);
        settingsPostBody.setVisibility(publicSwitch.isChecked());
        settingsPostBody.setNotification(notificationsSwitch.isChecked());
        settingsPostBody.setOnline(onlineSwitch.isChecked());

        SettingsService settingsService = retrofit.create(SettingsService.class);
        Call<GenericPostResponse> callSettings = settingsService.updateSettings(settingsPostBody);

        callSettings.enqueue(new Callback<GenericPostResponse>() {
            @Override
            public void onResponse(Call<GenericPostResponse> call, Response<GenericPostResponse> response) {

                if (response.body().getStatus().equals("200")) {

                    Toast.makeText(getActivity(), "Your settings are saved successfully.", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getActivity(),"Something went wrong, please try again !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GenericPostResponse> call, Throwable t) {
            }

        });

    }

}
