package cmpe277.sjsu.edu.teamproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.Services.FriendRequestService;
import cmpe277.sjsu.edu.teamproject.model.CurrentUserSessionModel;
import cmpe277.sjsu.edu.teamproject.model.FriendRequestDecisionRequest;
import cmpe277.sjsu.edu.teamproject.model.FriendRequestModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<FriendRequestModel> friendRequestList;

    public FriendRequestAdapter(Context context, ArrayList<FriendRequestModel> friendRequestList) {
        this.context = context;
        this.friendRequestList = friendRequestList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_friend_request, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FriendRequestModel model = friendRequestList.get(position);

        holder.screenNameTextView.setText(model.getName());

        holder.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Call confirm request API

                friendRequestList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, friendRequestList.size());

                //call post method to post friend request decision -- postitive
               // IsConfirmFriendRequest("YES",model.getNewfriendemailid());

            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Call delete request API

                friendRequestList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, friendRequestList.size());

                //call post method to post friend request decision -- postitive
               // IsConfirmFriendRequest("NO",model.getNewfriendemailid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendRequestList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileImageView;
        private TextView screenNameTextView;
        private Button confirmButton, deleteButton;

        public MyViewHolder(View view) {
            super(view);

            profileImageView = (ImageView) view.findViewById(R.id.profile_imageview);
            screenNameTextView = (TextView) view.findViewById(R.id.fragment_friend_request_name_text);
            confirmButton = (Button) view.findViewById(R.id.fragment_friend_request_confirm_text);
            deleteButton = (Button) view.findViewById(R.id.fragment_friend_request_delete_text);
        }
    }

    public void IsConfirmFriendRequest(String decision,String newfriendemailid){


        FriendRequestDecisionRequest friendRequestDecisionRequest = new FriendRequestDecisionRequest();
        friendRequestDecisionRequest.setDecision(decision);
        friendRequestDecisionRequest.setNewfriendemailid(newfriendemailid);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.241.140.236:3009")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FriendRequestService friendRequestService = retrofit.create(FriendRequestService.class);

        Call<ResponseBody> friendrequestdecision = friendRequestService.friendrequestdecision(CurrentUserSessionModel.LoggedEmail,friendRequestDecisionRequest);
        friendrequestdecision.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                // Toast on activity


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                // Toast on activity

            }
        });


    }

}
 