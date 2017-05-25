package cmpe277.sjsu.edu.teamproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.model.Post;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Post> timelineFeedList;


    public TimelineRecyclerViewAdapter(Context context, List<Post> feedList)
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
        final Post model = timelineFeedList.get(position);

        holder.authorNameTextView.setText(model.getAuthorName());
        holder.postTimeStampTextView.setText(model.getTimestamp());
        holder.postTitleTextView.setText(model.getContent());

        Glide.with(context.getApplicationContext())
                .load(model.getAuthorProfileImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.authorImageView);

        if (model.getMediaURL()==null || model.getMediaURL().equals("")) {

            holder.postImageView.setVisibility(View.GONE);
        } else {

            holder.postImageView.setVisibility(View.VISIBLE);
            Glide.with(context.getApplicationContext())
                    .load(model.getMediaURL())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.postImageView);

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
