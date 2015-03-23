package example.user.githubclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import example.user.githubclient.Models.Commits;


/**
 * Created by User on 23.03.2015.
 */
public class CommitsAdapter extends ArrayAdapter<Commits> {

    private Context context;
    private List<Commits> commitsList;

    public CommitsAdapter(Context context, int resource, List<Commits> objects) {
        super(context, resource, objects);
        this.context = context;
        this.commitsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_commit, parent, false);

        Commits message = commitsList.get(position);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        tv_message.setText(String.valueOf(message.getCommit().getMessage()));

        Commits author = commitsList.get(position);
        TextView tv_author = (TextView) view.findViewById(R.id.tv_author);
        tv_author.setText(String.valueOf(author.getCommit().getCommitter().getName()));

        Commits commitDate = commitsList.get(position);
        TextView tv_commit_date = (TextView) view.findViewById(R.id.tv_commit_date);
        tv_commit_date.setText(String.valueOf(commitDate.getCommit().getCommitter().getDate()));


        return view;
    }
}
