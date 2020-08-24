package tech.iwish.shyafinance.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import tech.iwish.shyafinance.R;

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    String loanId;

    public MainFragment(String loanId){
        this.loanId = loanId;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.main_fragment, null);
        InitaiteFragment(view);
        SetFragmentData();
        FragmentAction();



        return view;
    }

    public void InitaiteFragment(View view){
        recyclerView= view.findViewById(R.id.recycle);
    }
    public void FragmentAction(){

    }
    public void SetFragmentData(){

    }
}
