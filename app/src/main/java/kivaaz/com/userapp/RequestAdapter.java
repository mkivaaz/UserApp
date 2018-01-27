package kivaaz.com.userapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kivaaz.com.request.RequestList;

/**
 * Created by Muguntan on 11/24/2017.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.myViewHolder> {

    private LayoutInflater inflater;
    List<RequestList> data = new ArrayList<>();
    Context context;
    private OnItemClick mCallback;

    public RequestAdapter(List<RequestList> data, Context context, OnItemClick mCallback) {
        this.data = data;
        this.context = context;
        this.mCallback = mCallback;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.request_adapter,null);
        myViewHolder holder = new myViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.name.setText(data.get(position).getReqName());
        holder.desc.setText(data.get(position).getReqDesc());

        if(!data.get(position).getAcceptedBy().equals("None")){
            holder.acceptedby.setText(data.get(position).getAcceptedBy());
        }else{
            holder.acceptedby.setText("Not Taken");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView name, desc, acceptedby;
        public myViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.req_nameTV);
            desc = itemView.findViewById(R.id.req_descTV);
            acceptedby = itemView.findViewById(R.id.req_acceptedTV);
        }
    }
}
