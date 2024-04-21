package com.example.rucafe_androidapp;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
class DonutOptionsAdapter extends RecyclerView.Adapter<DonutOptionsAdapter.DonutOptionsHolder> {


    private Context context;
    private ArrayList<Donut> donuts;

    public DonutOptionsAdapter(Context context, ArrayList<Donut> donuts) {
        this.context = context;
        this.donuts = donuts;

    }

    @NonNull
    @Override
    public DonutOptionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donuts_view,parent,false);
        return new DonutOptionsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutOptionsHolder holder, int position) {
        holder.donut_flavor.setText(donuts.get(position).getFlavor());
        String price = String.valueOf(donuts.get(position).getBasePricePrice());
        holder.donut_price.setText(price);
        holder.donut_image.setImageResource(donuts.get(position).getImage());
        holder.donut_type.setText(donuts.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    public static class DonutOptionsHolder extends RecyclerView.ViewHolder {

        private ImageView donut_image;
        private TextView donut_flavor;
        private TextView donut_price;
        private TextView donut_type;
        private Spinner donut_quantity;
        private Button add_btn;
        private ConstraintLayout parentLayout;

        public DonutOptionsHolder(@NonNull View itemView) {
            super(itemView);
            donut_image = itemView.findViewById(R.id.donut_image);
            donut_flavor = itemView.findViewById(R.id.donut_flavor);
            donut_price = itemView.findViewById(R.id.donut_price);
            donut_quantity = itemView.findViewById(R.id.donut_quantity);
            donut_type = itemView.findViewById(R.id.donut_type);
            parentLayout = itemView.findViewById(R.id.donutRowLayout);
            add_btn = itemView.findViewById(R.id.add_btn);
            setAddButtonOnClick(itemView);

            donut_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }

        private void setAddButtonOnClick(@NonNull View itemView) {
            add_btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //Toast.makeText(view.getContext(),"Added to cart",Toast.LENGTH_SHORT).show();
                    DecimalFormat df = new DecimalFormat("#.##");
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Are you sure you want to order this item?");
                    double total_price = Double.parseDouble(donut_price.getText().toString()) *
                            Integer.parseInt(donut_quantity.getSelectedItem().toString());
                    alert.setMessage("Price: $"+df.format(total_price));

                    CartList cartList = CartList.getInstance();

                    Donut donut = new Donut(donut_type.getText().toString(),
                            donut_flavor.getText().toString(), Double.parseDouble(donut_price.getText().toString()),
                            Integer.parseInt(donut_quantity.getSelectedItem().toString()));
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            cartList.addItem(donut);
                            Toast.makeText(itemView.getContext(),"items in cart: "+cartList.getItems().size(),Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),"not added",Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }


    }
}
