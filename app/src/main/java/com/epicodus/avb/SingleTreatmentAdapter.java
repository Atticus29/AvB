package com.epicodus.avb;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SingleTreatmentAdapter extends BaseAdapter{
    private Context context;
    private String[] treatments;

    public SingleTreatmentAdapter(Context context, String[] treatments){
        this.context = context;
        this.treatments = treatments;
    }

    @Override
    public int getCount() {
        return treatments.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View treatmentGrid;
        if(convertView == null){
            treatmentGrid = inflater.inflate(R.layout.single_treatment_of_experiment, null);
            TextView treatmentView = (TextView) treatmentGrid
                    .findViewById(R.id.treatmentName);
            treatmentView.setText(treatments[position]);
        }else{
            treatmentGrid = (View) convertView;
        }
        return treatmentGrid;
    }
}
