package com.example.task_native.adapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.task_native.databinding.ItemLoadingBinding;
import com.example.task_native.model.Repository;
import com.example.task_native.R;
import com.example.task_native.databinding.RepositoryitemBinding;
import com.example.task_native.viewModel.RepositoryViewModel;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder>   implements Filterable {


    ArrayList<Repository> repositoryArrayList;
   public  Repository repository;
    Context context;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private OnLoadMoreListener onLoadMoreListener;
    private List<Repository> contactListFiltered;
    private ArrayList<Repository> Cashedlist = new ArrayList<>();
    private boolean isMoreLoading = true;


    public interface OnLoadMoreListener{
        void onLoadMore();
    }






    public RecyclerAdapter(Context context , OnLoadMoreListener onLoadMoreListener ){
        this.repositoryArrayList = new ArrayList<>();
        this.onLoadMoreListener = onLoadMoreListener;

        this.context = context;
        notifyDataSetChanged();
    }



    public void setData(List<Repository> userList){
        this.repositoryArrayList.addAll(userList) ;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RepositoryitemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.repositoryitem,
                viewGroup , false);

        ItemLoadingBinding binding2 = DataBindingUtil.inflate(LayoutInflater.from(context) ,
                R.layout.item_loading,
                viewGroup , false);

        return (i == VIEW_ITEM)? new viewHolder(binding,null): new viewHolder(null,binding2);


              }


    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {


        repository = repositoryArrayList.get(i);
        if(viewHolder.binding2 == null){
            viewHolder.binding.setRepo(repositoryArrayList.get(i));
            viewHolder.binding.setViewmodel(new RepositoryViewModel(context , null));


        }
        else
            viewHolder.binding2.setProgress(repositoryArrayList.get(i));

    }





    @Override
    public int getItemCount() {
        return repositoryArrayList == null ? 0 : repositoryArrayList.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{
      RepositoryitemBinding binding;
      ItemLoadingBinding binding2;
        public viewHolder(@NonNull RepositoryitemBinding itemView , ItemLoadingBinding itemView2) {
            super((itemView == null)?itemView2.getRoot():itemView.getRoot());
            if(itemView2 == null)
                binding = itemView;
            else{
                binding2 = itemView2;

            }



        }
    }



    @Override
    public int getItemViewType(int position) {
        return repositoryArrayList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }


    public void showLoading() {
        if (isMoreLoading && repositoryArrayList != null && onLoadMoreListener != null) {
            isMoreLoading = false;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    repositoryArrayList.add(null);
                    notifyItemInserted(repositoryArrayList.size() - 1);
                    onLoadMoreListener.onLoadMore();
                }
            });
        }
    }

    public void setMore(boolean isMore) {
        this.isMoreLoading = isMore;
    }

    public void dismissLoading() {
        if (repositoryArrayList != null && repositoryArrayList.size() > 0) {
            repositoryArrayList.remove(repositoryArrayList.size() - 1);
            notifyItemRemoved(repositoryArrayList.size());
        }
    }


    public void addItemMore(List<Repository> lst){
        int sizeInit = repositoryArrayList.size();
        repositoryArrayList.addAll(lst);
        notifyItemRangeChanged(sizeInit, repositoryArrayList.size());
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {

                    repositoryArrayList.addAll(Cashedlist);


                    System.out.println("EMPTY IS "+ repositoryArrayList.size());
                } else {

                    List<Repository> repositoryFiltered = new ArrayList<>();
                    for (Repository row : repositoryArrayList) {
                        if (row.getFull_name().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getOwner().getName().contains(charSequence)) {
                            repositoryFiltered.add(row);
                        }
                    }

                    contactListFiltered = repositoryFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Cashedlist.addAll(repositoryArrayList);
                repositoryArrayList.clear();
                repositoryArrayList = (ArrayList<Repository>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}




