package tarce.myodooapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by admin on 2017/1/2.
 */

public class RecycleViewActivity extends Activity {

    @InjectView(R.id.recycleView)
    RecyclerView mRecycleView;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_layout);
        ButterKnife.inject(this);
        initData();
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        final HomeAdapter adapter = new HomeAdapter();
        mRecycleView.setAdapter(adapter);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }

            @Override
            public void onItemLongClick(View view, int postion) {
                adapter.removeData(postion);
            }
        });
    }
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int postion);
        void onItemLongClick(View view, int postion);
    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {
        private OnItemClickListener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    RecycleViewActivity.this).inflate(R.layout.recycleitem, parent,
                    false));
            return holder;
        }
        public void addData(int position) {
            mDatas.add(position, "Insert One");
            notifyItemInserted(position);
        }

        public void removeData(int position) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }


        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position));
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(view,layoutPosition);
                }
            });
            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(view,layoutPosition);
                    return false;
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {


            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.recycleViewText);
            }
        }
    }


}
