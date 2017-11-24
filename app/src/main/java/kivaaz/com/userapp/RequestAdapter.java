package kivaaz.com.userapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        holder.email.setText(data.get(position).getReqEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView name, desc, email;
        public myViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.req_nameTV);
            desc = itemView.findViewById(R.id.req_descTV);
            email = itemView.findViewById(R.id.req_emailTV);
        }
    }
}
