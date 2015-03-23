package example.user.githubclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import example.user.githubclient.Models.Repositories;

/**
 * Created by alexey.bukin on 20.03.2015.
 */
public class RepoAdapter extends ArrayAdapter<Repositories> {

    private Context context;
    private List<Repositories> repositoriesList;

    public RepoAdapter(Context context, int resource, List<Repositories> objects) {
        super(context, resource, objects);
        this.context = context;
        this.repositoriesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_repo, parent, false);

        Repositories image = repositoriesList.get(position);
        ImageView iv_owner = (ImageView) view.findViewById(R.id.iv_owner);
        Picasso.with(context).load(image.getOwner().getAvatar_url())
                .resize(80, 80).centerCrop().into(iv_owner);

        Repositories owner = repositoriesList.get(position);
        TextView tv_owner = (TextView) view.findViewById(R.id.tv_owner);
        tv_owner.setText(String.valueOf(owner.getOwner().getLogin()));

        Repositories name = repositoriesList.get(position);
        TextView tv_repo_name = (TextView) view.findViewById(R.id.tv_repo_name);
        tv_repo_name.setText(String.valueOf(name.getName()));

        Repositories descriprion = repositoriesList.get(position);
        TextView tv_repo_description = (TextView) view.findViewById(R.id.tv_repo_description);
        tv_repo_description.setText(String.valueOf(descriprion.getDescription()));

        Repositories watchers = repositoriesList.get(position);
        TextView tv_watchers = (TextView) view.findViewById(R.id.tv_watchers);
        tv_watchers.setText(String.valueOf(watchers.getWatchersCount()));

        Repositories forks = repositoriesList.get(position);
        TextView tv_forks = (TextView) view.findViewById(R.id.tv_forks);
        tv_forks.setText(String.valueOf(forks.getForksCount()));

        return view;
    }

}
