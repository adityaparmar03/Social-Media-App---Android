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

public class SeeAllFriendsAdapter extends RecyclerView.Adapter<SeeAllFriendsAdapter.MyViewHolder> {

    private Context context;
    private List<FriendRequest> allFriendsList;

    public SeeAllFriendsAdapter(Context context, List<FriendRequest> allFriendsList) {
        this.context = context;
        this.allFriendsList = allFriendsList;
    }

    @Override
    public SeeAllFriendsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_friend_request, parent, false);

        return new SeeAllFriendsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SeeAllFriendsAdapter.MyViewHolder holder, final int position) {

        final FriendRequest model = allFriendsList.get(position);

        holder.screenNameTextView.setText(model.getScreenName());
        holder.confirmButton.setVisibility(View.GONE);
        holder.deleteButton.setVisibility(View.GONE);
        Glide.with(context.getApplicationContext())
                .load("IMAGE URL HERE")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.profileImageView);


    }

    @Override
    public int getItemCount() {

        return allFriendsList.size();
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
