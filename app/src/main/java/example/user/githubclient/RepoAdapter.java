//package example.user.githubclient;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.util.LruCache;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.util.List;
//
//import example.user.githubclient.Activity.MainActivity;
//import example.user.githubclient.Models.Repo;
//
///**
// * Created by alexey.bukin on 20.03.2015.
// */
//public class RepoAdapter extends ArrayAdapter<Repo> {
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
