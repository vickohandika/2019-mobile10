package id.ac.polinema.skorbasket.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import id.ac.polinema.skorbasket.R;
import id.ac.polinema.skorbasket.viewmodels.SharedScore;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinnerFragment extends Fragment {

	private TextView txtWinner;
	private Button btnReset;
	private SharedScore sharedScore;
	private Integer scoreHome=0;
	private Integer scoreVisitor=0;

	public WinnerFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedScore =  ViewModelProviders.of(requireActivity()).get(SharedScore.class);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_winner, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		txtWinner = view.findViewById(R.id.txtWinner);
		btnReset = view.findViewById(R.id.btnReset);

		sharedScore.getScoreVisitor().observe(requireActivity(), new Observer<Integer>() {
			@Override
			public void onChanged(Integer score) {
				scoreVisitor = score;
				if(scoreVisitor > scoreHome){
					sharedScore.setWinner(false);
				}
			}
		});

		sharedScore.getScoreHome().observe(requireActivity(), new Observer<Integer>() {
			@Override
			public void onChanged(Integer score) {
				scoreHome = score;
				if(scoreVisitor < scoreHome){
					sharedScore.setWinner(true);
				}
			}
		});

		sharedScore.getWinner().observe(requireActivity(), new Observer<Boolean>() {
			@Override
			public void onChanged(Boolean winner) {
				if (winner){
					txtWinner.setText("Home Win");
				} else{
					txtWinner.setText("Visitor Win");
				}
			}
		});

		btnReset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sharedScore.setScoreVisitor(0);
				sharedScore.setScoreHome(0);
			}
		});
	}

}
