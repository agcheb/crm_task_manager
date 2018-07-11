package com.gb.students.crm_task_manager.contacts.addcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gb.students.crm_task_manager.R;

import java.util.ArrayList;

public class ListViewCustomAdapter  extends BaseAdapter {

    private Context context;
    private ArrayList<AdditionalFields> imageModelArrayList;


    public ListViewCustomAdapter(Context context, ArrayList<AdditionalFields> imageModelArrayList) {

        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.addcontact_list_item, null, true);

            holder.name = (TextView) convertView.findViewById(R.id.add_contact_title_item);
            holder.image = (ImageView) convertView.findViewById(R.id.add_contact_imageView);
            holder.count = (TextView) convertView.findViewById(R.id.add_contact_count);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.name.setText(imageModelArrayList.get(position).getName());
        holder.image.setImageResource(imageModelArrayList.get(position).getImage_drawable());


        return convertView;
    }

    private class ViewHolder {

        protected TextView name;
        private ImageView image;
        private TextView count;

    }

}
