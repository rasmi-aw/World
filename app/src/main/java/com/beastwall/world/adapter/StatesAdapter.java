package com.beastwall.world.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beastwall.localisation.model.State;
import com.beastwall.world.R;

import java.util.List;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * States list adapter
 */
public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.StateHolder> {
    private List<State> states;

    public StatesAdapter(List<State> states) {
        this.states = states;
        Log.v("rasmi",""+states.size());
    }

    @NonNull
    @Override
    public StateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.state, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StateHolder holder, int position) {
        State state = states.get(position);
        //
        holder.name.setText(state.getName());
    }

    @Override
    public int getItemCount() {
        return states == null ? 0 : states.size();
    }

    /**
     * StateHolder
     */
    public static class StateHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public StateHolder(@NonNull View itemView) {
            super(itemView);
            //
            name = itemView.findViewById(R.id.name);
        }
    }



}
