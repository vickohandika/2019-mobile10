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
public class VisitorFragment extends Fragment {

	private Button scoreDuaVisitor;
	private Button scoreTigaVisitor;
	private Button scoreSatuVisitor;
	private TextView scoreVisitor;
	private SharedScore sharedScore;
	private int scoreDefault = 0;

	public VisitorFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedScore = ViewModelProviders.of(requireActivity()).get(SharedScore.class);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_visitor, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		scoreDuaVisitor = view.findViewById(R.id.scoreDuaVisitor);
		scoreTigaVisitor = view.findViewById(R.id.scoreTigaVisitor);
		scoreVisitor = view.findViewById(R.id.scoreVisitor);
		scoreSatuVisitor = view.findViewById(R.id.scoreSatuVisitor);


		// Tambahkan logic tombol di bagian bawah ini

		sharedScore.getScoreVisitor().observe(requireActivity(), new Observer<Integer>() {
			@Override
			public void onChanged(Integer score) {
				scoreVisitor.setText(String.valueOf(score));
				scoreDefault = score;
			}
		});

		scoreDuaVisitor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sharedScore.setScoreVisitor(scoreDefault+2);
			}
		});

		scoreTigaVisitor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sharedScore.setScoreVisitor(scoreDefault+3);
			}
		});

		scoreSatuVisitor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sharedScore.setScoreVisitor(scoreDefault+1);
			}
		});

	}
}
