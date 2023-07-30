package com.example.salarypredictor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.salarypredictor.databinding.FragmentPredictionPageFragmentBinding;
import com.example.salarypredictor.databinding.UserInputFragmentBinding;

public class PredictionFragment extends Fragment {

    private FragmentPredictionPageFragmentBinding binding;
   private TextView amountTextview ;
    String predictionText = "";
    private TextView prone_textview;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPredictionPageFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        amountTextview = view.findViewById(R.id.predictedsalary);

        Bundle args = getArguments();
            String text = args.getString("prediction");
            System.out.println("text: "+ text);
        amountTextview.setText("$" + text + "k/yr");



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}