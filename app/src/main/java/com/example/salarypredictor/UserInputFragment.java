package com.example.salarypredictor;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.salarypredictor.databinding.UserInputFragmentBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserInputFragment extends Fragment {
    Set<String> uniqueValues = new HashSet<>(); // Use a Set to store unique values
    String[] inputArr ;
    private UserInputFragmentBinding binding;
    //UI variables
        Spinner degreeSpinner;
        Spinner jobtypeSpinner;
        Spinner majorSpinner;
        Spinner industrySpinner;
        Spinner yoeSpinner;
    // temp vars for ui
    //holds the selected option from dropdown
    String selectedDegree = "";
    String selectedYOE = "";
    String selectedMajor = "";
    String selectedIndustry = "";
    String selectedJobtype = "";
    Button submitButton;
    String apiEndpoint = "http://10.0.2.2:5000/predict";
    String predictionText = "";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = UserInputFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //connects code with the UI
        majorSpinner = view.findViewById(R.id.major_spinner);
        degreeSpinner = view.findViewById(R.id.degree_select_spinner);
        yoeSpinner = view.findViewById(R.id.year_select_spinner);
        jobtypeSpinner = view.findViewById(R.id.jobtype_spinner);
        industrySpinner = view.findViewById(R.id.industry_spinner);
        submitButton = view.findViewById(R.id.submit_input_button);


        ArrayAdapter<CharSequence> majorAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.major_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> degreeAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.degree_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> industryAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.industry_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence>  yoeAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.years, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> jobtypeAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.jobtype_array, android.R.layout.simple_spinner_item);

        //sets the drop down and allows array to work with spinner
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yoeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobtypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //sets spinners array
        majorSpinner.setAdapter(majorAdapter);
        yoeSpinner.setAdapter(yoeAdapter);
        degreeSpinner.setAdapter(degreeAdapter);
        jobtypeSpinner.setAdapter(jobtypeAdapter);
        industrySpinner.setAdapter(industryAdapter);

        //stores and ouputts selected user input from the spinner
        majorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Use the selectedItem as needed
                // For example, display it in a Toast message
                selectedMajor = selectedItem;
                System.out.println(selectedMajor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when no item is selected
            }
        });
        yoeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Use the selectedItem as needed
                // For example, display it in a Toast message
                selectedYOE = selectedItem;
                System.out.println(selectedYOE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when no item is selected
            }
        });
        degreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedDegree = selectedItem;
                System.out.println(selectedYOE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        industrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Use the selectedItem as needed
                // For example, display it in a Toast message
                selectedIndustry = selectedItem;
                System.out.println(selectedIndustry);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when no item is selected
            }
        });
        jobtypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Use the selectedItem as needed
                // For example, display it in a Toast message
                selectedJobtype = selectedItem;
                System.out.println(selectedJobtype);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when no item is selected
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                //creates json string
                //creates the request body for the model to process
                RequestBody body = RequestBody.create(mediaType, "{\r\n \"degree\":\""+selectedDegree+"\",\r\n \"major\":\""+selectedMajor+
                        "\",\r\n \"jobType\":\""+selectedJobtype+"\",\r\n \"industry\":\""+selectedIndustry+
                        "\",\r\n \"yoe\":\""+selectedYOE+
                        "\"\r\n}");
                Request request = new Request.Builder()
                        .url(apiEndpoint)
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Bundle args = new Bundle();
                    predictionText =  responseData.substring(1, 4);
                    System.out.println("type : "+ predictionText);
                    args.putString("prediction", predictionText);
                    System.out.println("prediction: " + predictionText);
                    NavHostFragment.findNavController(UserInputFragment.this).navigate(R.id.action_SecondFragment_to_ThirdFragment,args);

                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}