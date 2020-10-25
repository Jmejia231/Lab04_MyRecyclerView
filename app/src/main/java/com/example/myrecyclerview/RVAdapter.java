package com.example.myrecyclerview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonaViewHolder> {

    List<Equipo> equipos;
    Context context;

    public RVAdapter(List<Equipo> equipos, Context context) {
        this.equipos = equipos;
        this.context = context;
    }

    @NonNull
    @Override
    public RVAdapter.PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona,parent, false);

        PersonaViewHolder personaViewHolder = new PersonaViewHolder(view);
        return personaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.PersonaViewHolder holder, int position) {

        holder.nombreEquipo.setText(equipos.get(position).nombre);
        holder.paisEquipo.setText(equipos.get(position).pais);
        holder.botonDetalle.setId(equipos.get(position).botonId);
        holder.descripcion.setText(equipos.get(position).descripcion);

        Drawable original = context.getResources().getDrawable(equipos.get(position).foto, null);

        Bitmap originalBitmap = ((BitmapDrawable)original).getBitmap();
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(),originalBitmap);
        roundedBitmapDrawable.setCornerRadius(originalBitmap.getHeight());

        holder.fotoEquipo.setImageDrawable(roundedBitmapDrawable);
        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView nombreEquipo;
        TextView paisEquipo;
        ImageView fotoEquipo;
        Button botonDetalle;
        TextView descripcion;
        Dialog dialog;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cv);
            nombreEquipo = (TextView)itemView.findViewById(R.id.nombrePersona);
            paisEquipo = (TextView)itemView.findViewById(R.id.edadPersona);
            fotoEquipo = (ImageView)itemView.findViewById(R.id.fotoPersona);
            botonDetalle = (Button) itemView.findViewById(R.id.btnDetalle);
            descripcion = (TextView) itemView.findViewById(R.id.txtdetalle);
        }

        public void setOnClickListeners() {
            botonDetalle.setOnClickListener(this);
        }

        public void onClick(View v) {
            crearDialog(v,descripcion);
        }

        private void crearDialog(final View itemView, TextView descripcion) {
            int width = (int)(itemView.getResources().getDisplayMetrics().widthPixels*0.90);
            int height = (int)(itemView.getResources().getDisplayMetrics().heightPixels*0.50);
            dialog = new Dialog(itemView.getContext());
            dialog.setContentView(R.layout.titulos);
            dialog.setTitle(nombreEquipo.getText().toString());
            TextView text = (TextView) dialog.findViewById(R.id.txtTitulo);
            TextView txtDescripcion = (TextView) dialog.findViewById(R.id.txtDescripcion);
            txtDescripcion.setText(descripcion.getText().toString());
            text.setText(nombreEquipo.getText().toString());
            Button cerrar = (Button)dialog.findViewById(R.id.btnRegresar);
            cerrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Dismiss the popup window
                    dialog.dismiss();
                }
            });
            txtDescripcion.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            dialog.show();

        }
    }
}