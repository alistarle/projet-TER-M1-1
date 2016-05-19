package com.example.vladimirkarassouloff.projetter.ui.myviews.gridview.landscapealgoview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vladimirkarassouloff.projetter.R;

/**
 * Created by Vladimir on 18/05/2016.
 */

public class ImageAdapterAlgoView extends BaseAdapter
{
    Context context;
    LandscapeAlgoView lav;
    public ImageAdapterAlgoView(Context _MyContext,LandscapeAlgoView lav)
    {
        this.context = _MyContext;
        this.lav = lav;

    }

    @Override
    public int getCount()
    {
        return lav.getChildCount()+1;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater mInflater = LayoutInflater.from(context);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gridcell, parent,false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.grid_item_text);
            holder.icon = (ImageView) convertView.findViewById(R.id.grid_item_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
        holder.text.setText("Position "+position);
        //holder.text.setText(getTextId(position));
        holder.icon.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), getIconId(position)));
        if(getIconId(position) == R.drawable.test) {
            convertView.setVisibility(View.GONE);
        }

       /* if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.gridcell, parent, false);

        }
        return convertView;*/
        return convertView;
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    private int getIconId(int position) {
        int iconImages[] = {
                R.drawable.black,
                R.drawable.black,
                R.drawable.black,
                R.drawable.black,
                R.drawable.black,
                R.drawable.black,
                R.drawable.black,
                R.drawable.black,
                R.drawable.black,
                R.drawable.black,
                R.drawable.loop_back

        };
        return iconImages[position%iconImages.length];
    }

    private int getTextId(int position) {
        int iconNames[] = {
                R.string.hello_world,
                R.string.hello_world,
                R.string.hello_world,
                R.string.hello_world,
                R.string.hello_world,
                R.string.hello_world,
                R.string.hello_world,
                R.string.hello_world,
                R.string.hello_world,
                R.string.hello_world
        };
        return iconNames[position%iconNames.length];
    }

}