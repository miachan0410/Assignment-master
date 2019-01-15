package my.edu.taruc.assignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
    private String StudentID ="";
    private String StudentName ="";
    private String StudentEmail ="";
    private String DriverRating = "" ;
    private String RatingCount = "" ;

    private double DRating,DRatingCount,tempRating;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences prefs = getActivity().getSharedPreferences("PrefText", MODE_PRIVATE);
        StudentName = prefs.getString("StudentName", "No name defined");
        StudentID = prefs.getString("StudentID", "No ID defined");
        StudentEmail = prefs.getString("Email", "No Email defined");
        DriverRating = prefs.getString("Rating", "No Rating defined");
        RatingCount = prefs.getString("RatingCount", "No RatingCount defined");

        return inflater.inflate(R.layout.fragment_profile,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView studName = getView().findViewById(R.id.studentName);
        studName.setText(StudentName);
        TextView studID = getView().findViewById(R.id.studentID);
        studID.setText(StudentID);
        TextView studEmail = getView().findViewById(R.id.studentEmail);
        studEmail.setText(StudentEmail);

        DRating = Double.parseDouble(DriverRating);
        DRatingCount = Double.parseDouble(RatingCount);

        if(DRating !=0) {
            tempRating = DRating / (DRatingCount * 5)*100;
        }else{
            tempRating = 0;
        }
        TextView RatingC = getView().findViewById(R.id.driverRating);
        RatingC.setText(String.valueOf(tempRating));
    }


}
