package example.user.githubclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import example.user.githubclient.Models.Repositories;

/**
* Created by alexey.bukin on 20.03.2015.
*/
public class RepoAdapter extends ArrayAdapter<Repositories> {

private Context context;
    private List<Repositories> repositoriesList;
    public RepoAdapter(Context context, int resource, List<Repositories> objects){
        super(context, resource,objects);
        this.context = context;
        this.repositoriesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_repo, parent, false);

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
        tv_watchers.setText(Integer.valueOf(watchers.getWatchersCount()));

        Repositories forks = repositoriesList.get(position);
        TextView tv_forks = (TextView) view.findViewById(R.id.tv_forks);
        tv_forks.setText(Integer.valueOf(forks.getForksCount()));

        return view;
    }

}
//
//    private Context context;
//    private List<Repo> repoList;
//
//    private LruCache<Integer, Bitmap> imageCache;
//
//    public RepoAdapter(Context context, int resource, List<Repo> objects) {
//        super(context, resource, objects);
//        this.context = context;
//        this.repoList = objects;
//
//        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//        final int cacheSize = maxMemory / 8;
//        imageCache = new LruCache<>(cacheSize);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater =
//                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.item_repo, parent, false);
//
//        //display name
//
//        Repo repo = repoList.get(position);
//        TextView tv = (TextView) view.findViewById(R.id.tv_repo_name);
//        tv.setText(repo.getFull_name());
//
//        Bitmap bitmap = imageCache.get(repo.getProductId());
//        if (bitmap != null) {
//            ImageView image = (ImageView) view.findViewById(R.id.imageView1);
//            image.setImageBitmap(repo.getBitmap());
//
//        } else {
//            ReposAndAuthor container = new ReposAndAuthor();
//            container.full_name = full_name;
//            container.view = view;
//
//            ImageLoader loader = new ImageLoader();
//            loader.execute(container);
//        }
//
//        return view;
//    }
//
//    class ReposAndAuthor {
//        public FullName full_name;
//        public View view;
//        public Bitmap bitmap;
//        public Forks forks;
//        public Watches watches;
//
//    }
//
//    private class ImageLoader extends AsyncTask<ReposAndAuthor, Void, ReposAndAuthor> {
//
//        @Override
//        protected ReposAndAuthor doInBackground(ReposAndAuthor... params) {
//
//            ReposAndAuthor container = params[0];
//            FullName full_name = container.full_name;
//            try {
//                String imageUrl = MainActivity.PHOTOS_BASE_URL + flower.getPhoto();
//                InputStream in = (InputStream) new URL(imageUrl).getContent();
//                Bitmap bitmap = BitmapFactory.decodeStream(in);
//                full_name.setBitmap(bitmap);
//                in.close();
//                container.bitmap = bitmap;
//                return container;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//}
