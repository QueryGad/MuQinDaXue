package com.player.muqindaxue.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.player.muqindaxue.R;
import com.player.muqindaxue.bean.OriginalBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */

public class OriginalAdapter extends BaseAdapter{

    private Context context;
    private RequestManager glideRequest;
    private List<OriginalBean.BooksBean> lists = new ArrayList<>();

    public OriginalAdapter(Context context,List lists) {
        super();
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = null;
        OriginalHolder holder = null;
        if (convertView==null){
            view = View.inflate(context, R.layout.item_original,null);
            holder = new OriginalHolder();
            holder.iv_original = (ImageView) view.findViewById(R.id.iv_original);
            holder.iv_original_author = (ImageView) view.findViewById(R.id.iv_original);
            holder.tv_original_title = (TextView) view.findViewById(R.id.tv_original_title);
            holder.tv_original_author = (TextView) view.findViewById(R.id.tv_original_author);
            holder.tv_original_date = (TextView) view.findViewById(R.id.tv_original_date);
            holder.tv_original_viewcount = (TextView) view.findViewById(R.id.tv_original_viewcount);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (OriginalHolder) view.getTag();
        }
        Glide.with(context).load(lists.get(position).getImg())
                .into(holder.iv_original);
        holder.tv_original_title.setText(lists.get(position).getTitle());
        holder.tv_original_author.setText(lists.get(position).getEditor());
        holder.tv_original_date.setText(lists.get(position).getDate());
        holder.tv_original_viewcount.setText(lists.get(position).getViewCount()+"已看");
        return view;
    }

    class OriginalHolder{
        private ImageView iv_original,iv_original_author;
        private TextView tv_original_title,tv_original_author,tv_original_date,tv_original_viewcount;
    }
}
