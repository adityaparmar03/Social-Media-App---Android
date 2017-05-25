package cmpe277.sjsu.edu.teamproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.model.PostModel;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<PostModel> timelineFeedList;


    public TimelineRecyclerViewAdapter(Context context, ArrayList<PostModel> feedList)
    {
        this.context = context;
        timelineFeedList = feedList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_timeline, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final PostModel model = timelineFeedList.get(position);

        holder.authorNameTextView.setText(model.getScreenname());
        holder.postTimeStampTextView.setText(model.getDatetime());
        holder.postTitleTextView.setText(model.getMessage());

        if (model.getMedia().equals(" ")) {

            holder.postImageView.setVisibility(View.VISIBLE);
        } else {

            holder.postImageView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return timelineFeedList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView authorImageView, postImageView;
        TextView authorNameTextView, postTimeStampTextView, postTitleTextView;

        MyViewHolder(View view) {
            super(view);

            authorImageView = (ImageView) view.findViewById(R.id.profile_pic_imageview);
            postImageView = (ImageView) view.findViewById(R.id.post_image_view);

            postTitleTextView = (TextView) view.findViewById(R.id.post_title_textview);
            authorNameTextView = (TextView) view.findViewById(R.id.author_name_textview);
            postTimeStampTextView = (TextView) view.findViewById(R.id.post_timestamp_textview);
        }
    }
}
