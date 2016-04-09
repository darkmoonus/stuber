package uet.vav.stuber.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import java.util.ArrayList;

import uet.vav.stuber.R;
import uet.vav.stuber.activities.MainActivity;
import uet.vav.stuber.activities.UserDetailActivity;
import uet.vav.stuber.customizes.MyTextView;
import uet.vav.stuber.models.User;

/**
 * Created by darkmoonus on 4/9/16.
 */
public class DirectListAdapter extends RecyclerView.Adapter<DirectListAdapter.DataObjectHolder> {
    private ArrayList<User> dataSet;
    private MainActivity mActivity;

    public DirectListAdapter(ArrayList<User> myDataSet, MainActivity mActivity) {
        this.mActivity = mActivity;
        this.dataSet = myDataSet;
    }

    @Override
    public int getItemViewType(int position) {
        return -1;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_direct_list, parent, false);
        return new DataObjectHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        final User u = dataSet.get(position);
        holder.name.setText(u.getName());
        holder.skills.setText(u.getSkills());
        holder.price.setText(String.valueOf(u.getHireRate()));
        holder.rating.setText(u.getRating() + "/5");
        holder.rating.invalidate();
        holder.hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, UserDetailActivity.class);
                intent.putExtra("uid", dataSet.get(position).getId());
                mActivity.startActivity(intent);
            }
        });
        holder.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void addItem(User dataObj, int index) {
        dataSet.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void addItem(User dataObj) {
        dataSet.add(0, dataObj);
        notifyItemInserted(0);
    }

    public void deleteItem(int index) {
        if (index >= 0 && index <= dataSet.size() - 1) {
            dataSet.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void clearData() {
        dataSet.clear();
    }

    public User getItem(int index) {
        return dataSet.get(index);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        private ImageView avatar, state;
        private MyTextView name, price, skills;
        private MyTextView rating;
        private MyTextView hire;
        private LinearLayout report;
        private CardView card;

        public DataObjectHolder(View itemView, int type) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            state = (ImageView) itemView.findViewById(R.id.state);
            name = (MyTextView) itemView.findViewById(R.id.name);
            price = (MyTextView) itemView.findViewById(R.id.price);
            skills = (MyTextView) itemView.findViewById(R.id.skills);
            rating = (MyTextView) itemView.findViewById(R.id.rating);
            hire = (MyTextView) itemView.findViewById(R.id.hire);
            report = (LinearLayout) itemView.findViewById(R.id.report);
            card = (CardView) itemView.findViewById(R.id.card);
        }
    }
}