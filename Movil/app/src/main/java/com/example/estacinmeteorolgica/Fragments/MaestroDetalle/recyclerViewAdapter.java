package com.example.estacinmeteorolgica.Fragments.MaestroDetalle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estacinmeteorolgica.R;

import java.util.ArrayList;

    public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolderData> implements View.OnClickListener {

        private ArrayList<LectureData> historial;
        private View.OnClickListener clickListener;

        public recyclerViewAdapter(ArrayList<LectureData> historial){
            this.historial = historial;
        }

        @NonNull
        @Override
        public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,null,false);

            ViewHolderData viewHolderData = new ViewHolderData(view);

            view.setOnClickListener(this);

            return viewHolderData;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {

            holder.temperatura.setText(historial.get(position).getTemperatura());
            holder.humedad.setText(historial.get(position).getHumedad());
            //holder.latitud.setText(historial.get(position).getLatitud());
            //holder.longitud.setText(historial.get(position).getLongitud());
            holder.fecha.setText(historial.get(position).getTiempo());
        }

        @Override
        public int getItemCount() {
            return historial.size();
        }

        @Override
        public void onClick(View v) {
            if(this.clickListener != null) {
                clickListener.onClick(v);
            }
        }

        public void setClickListener(View.OnClickListener listener) {
            this.clickListener = listener;
        }


        public class ViewHolderData extends RecyclerView.ViewHolder {

            private TextView temperatura, humedad, latitud, longitud, fecha;

            public ViewHolderData(@NonNull View itemView) {
                super(itemView);

                temperatura = (TextView) itemView.findViewById(R.id.txtvw_temp);
                humedad = (TextView) itemView.findViewById(R.id.txtvw_hum);
                //latitud = (TextView) itemView.findViewById(R.id.txtvw_latitud);
                //longitud = (TextView) itemView.findViewById(R.id.txtvw_longitud);
                fecha = (TextView) itemView.findViewById(R.id.txtvw_fecha2);
            }
        }
    }
