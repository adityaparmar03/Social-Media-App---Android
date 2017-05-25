package cmpe277.sjsu.edu.teamproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.model.FriendRequest;


public class SentRequestAdapter extends RecyclerView.Adapter<SentRequestAdapter.MyViewHolder> {

    private Context context;
    private List<FriendRequest> sentRequestList;

    public SentRequestAdapter(Context context, List<FriendRequest> sentRequestList) {
        this.context = context;
        this.sentRequestList = sentRequestList;
    }

    @Override
    public SentRequestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_friend_request, parent, false);

        return new SentRequestAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SentRequestAdapter.MyViewHolder holder, final int position) {

        final FriendRequest model = sentRequestList.get(position);

        holder.screenNameTextView.setText(model.getScreenName());
        holder.confirmButton.setVisibility(View.GONE);
        holder.deleteButton.setVisibility(View.GONE);

        Glide.with(context.getApplicationContext())
                .load("IMAGE URL HERE")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.profileImageView);


//        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FriendRequestConfirmReject friendRequestReject = new FriendRequestConfirmReject();
//                friendRequestReject.setRequestor(model.getEmail());
//                friendRequestReject.setSender(Session.LoggedEmail);
//
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(context.getString(R.string.base_url))
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//
//                FriendRequestService friendRequestService = retrofit.create(FriendRequestService.class);
//
//                Call<GenericPostResponse> callRejectFriendRequest = friendRequestService.rejectPendingFrndRequest(friendRequestReject);
//                callRejectFriendRequest.enqueue(new Callback<GenericPostResponse>() {
//                    @Override
//                    public void onResponse(Call<GenericPostResponse> call, Response<GenericPostResponse> response) {
//
//                        if (response.body().getStatus().equals("200")) {
//
//                            sentRequestList.remove(position);
//                            notifyItemRemoved(position);
//                            notifyItemRangeChanged(position, sentRequestList.size());
//                        } else {
//
//                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<GenericPostResponse> call, Throwable t) {
//
//                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//        });

    }

    @Override
    public int getItemCount() {

        return sentRequestList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileImageView;
        private TextView screenNameTextView;
        private Button confirmButton, deleteButton;

        MyViewHolder(View view) {
            super(view);

            profileImageView = (ImageView) view.findViewById(R.id.profile_imageview);
            screenNameTextView = (TextView) view.findViewById(R.id.screen_name_textview);
            confirmButton = (Button) view.findViewById(R.id.confirm_button);
            deleteButton = (Button) view.findViewById(R.id.delete_button);
        }
    }

}
