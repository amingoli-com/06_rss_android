package com.ermile.a06_rss_android.Adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ermile.a06_rss_android.Interface.ItemClickListener;
import com.ermile.a06_rss_android.MainActivity;
import com.ermile.a06_rss_android.Modle.RSSObject;
import com.ermile.a06_rss_android.R;
import com.ermile.a06_rss_android.Modle.RSSObject;
import com.ermile.a06_rss_android.web_view;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{

    public TextView txtTitle,txtPubDate,txtContent;
    private ItemClickListener itemClickListener;


    public FeedViewHolder(View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById ( R.id.txtTitle );
        txtPubDate = itemView.findViewById ( R.id.txtPubDate );
        txtContent = itemView.findViewById ( R.id.txtContent );


        //Set Event
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private RSSObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public FeedAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row,parent,false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, int position) {

        holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtPubDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.txtContent.setText(rssObject.getItems().get(position).getContent());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick)
                {

                    String link = rssObject.getItems ().get ( position ).getLink ();


                    if (treejon(link))
                    {
//                        Toast.makeText(view.getContext () , "اطلاعات ارسال شد!" , Toast.LENGTH_SHORT).show( );
                        Intent post_info_form = new Intent(view.getContext (), web_view.class);
                        post_info_form.putExtra("post_link" , link);

                        view.getContext ().startActivity ( post_info_form );

                    }
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
//                    view.getContext ().startActivity(browserIntent);
                }
            }
        });
    }

    private boolean treejon(String link) {

        if (link ==null){
            return false;
        }

        return true;
    }

    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}
