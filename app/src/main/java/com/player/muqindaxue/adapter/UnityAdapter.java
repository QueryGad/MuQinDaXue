package com.player.muqindaxue.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.player.muqindaxue.R;
import com.player.muqindaxue.bean.HotArticleBean;
import com.player.muqindaxue.utils.DateUtils;
import com.player.muqindaxue.view.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */

public class UnityAdapter extends BaseAdapter{

    private Context context;
    private List<HotArticleBean.TrendsBean> lists = new ArrayList<>();
    private RequestManager glideRequest;

    public UnityAdapter(Context context,List lists) {
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
        UnityHolder holder = null;
        if (convertView==null){
            view = View.inflate(context,R.layout.item_unity,null);
            holder = new UnityHolder();
            holder.iv_unityitem = (ImageView) view.findViewById(R.id.iv_unityitem);
            holder.tv_unityitem_name = (TextView) view.findViewById(R.id.tv_unityitem_name);
            holder.tv_unityitem_date = (TextView) view.findViewById(R.id.tv_unityitem_date);
            holder.tv_unityitem_cirle = (TextView) view.findViewById(R.id.tv_unityitem_cirle);
            holder.tv_unityitem_title = (TextView) view.findViewById(R.id.tv_unityitem_title);
            holder.tv_unityitem_desc = (TextView) view.findViewById(R.id.tv_unityitem_desc);
            holder.tv_unityitem_recordnum = (TextView) view.findViewById(R.id.tv_unityitem_recordnum);
            holder.tv_unityitem_commentnum = (TextView) view.findViewById(R.id.tv_unityitem_commentnum);
            holder.ng_nuity = (NineGridView) view.findViewById(R.id.ng_nuity);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (UnityHolder) view.getTag();
        }
        String uicon = lists.get(position).getUicon();
        if (uicon==null){
            holder.iv_unityitem.setImageResource(R.mipmap.ic_launcher);
        }else {
            glideRequest = Glide.with(context);
            glideRequest.load(uicon)
                    .transform(new GlideCircleTransform(context)).into(holder.iv_unityitem);
        }
        holder.tv_unityitem_name.setText(lists.get(position).getUniceName());
        holder.tv_unityitem_date.setText(DateUtils.getStandardDate(lists.get(position).getDatetime()));
        holder.tv_unityitem_cirle.setText(lists.get(position).getFrom());
        String title = lists.get(position).getTitle();
        if (title==""){
            holder.tv_unityitem_title.setVisibility(View.GONE);
        }else {
            holder.tv_unityitem_title.setText(title);
        }
        holder.tv_unityitem_desc.setText(lists.get(position).getContent());
        holder.tv_unityitem_recordnum.setText(lists.get(position).getViewCount()+"");
        holder.tv_unityitem_commentnum.setText(lists.get(position).getReviews().size()+"");
        //九宫格图片
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<String> imageDetails = lists.get(position).getPics();
        if (imageDetails != null) {
            for (String imageDetail : imageDetails) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(imageDetail);
                info.setBigImageUrl(imageDetail);
                imageInfo.add(info);
            }
        }
        holder.ng_nuity.setAdapter(new NineGridViewClickAdapter(context,imageInfo));
        return view;
    }

    class UnityHolder{
        private ImageView iv_unityitem;
        private TextView tv_unityitem_name,tv_unityitem_date,tv_unityitem_cirle,tv_unityitem_title,
                tv_unityitem_desc,tv_unityitem_recordnum,tv_unityitem_commentnum;
        private NineGridView ng_nuity;
    }
}
