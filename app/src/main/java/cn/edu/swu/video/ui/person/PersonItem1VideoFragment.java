package cn.edu.swu.video.ui.person;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.video.R;
import cn.edu.swu.video.javaBean.Video;

import static cn.edu.swu.video.utils.Util.setNetVideoBitmap;


public class PersonItem1VideoFragment extends Fragment {

    private RecyclerView mRecycler;
    public static LayoutInflater mLayoutInflater;
    private ItemAdapter mAdapter;
    private List<Video> mRows = new ArrayList<Video>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_person_item1_video, container, false);

        Video item = new Video();
        item.setSrc("http://vjs.zencdn.net/v/oceans.mp4");
        mRows.add(item);
        mRows.add(item);
        mRows.add(item);
        mRows.add(item);
        mRows.add(item);
        mRows.add(item);
        mRecycler = view.findViewById(R.id.recycler_view);
        mAdapter = new ItemAdapter(mRows);
        mLayoutInflater =  getLayoutInflater();
        mRecycler.setAdapter(mAdapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);


        return view;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImg;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.item_img);
        }
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{

        private List<Video> mData;

        public ItemAdapter(List<Video> mRows) {
            this.mData = mRows;
        }



        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.recyclerview_per_video,parent,false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            Video item = mData.get(position);
            setNetVideoBitmap(item.getSrc(),holder.mImg);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }






        }

    }
