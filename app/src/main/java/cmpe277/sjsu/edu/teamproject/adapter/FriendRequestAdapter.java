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
import cmpe277.sjsu.edu.teamproject.model.FriendRequestModel;

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
        FriendRequestModel model = friendRequestList.get(position);

        holder.screenNameTextView.setText(model.getName());

        holder.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Call confirm request API

                friendRequestList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, friendRequestList.size());
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Call delete request API

                friendRequestList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, friendRequestList.size());
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

}
 