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
public class HomeFragment extends Fragment {

	private Button scoreDuaHome;
	private Button scoreTigaHome;
	private Button scoreSatuHome;
	private TextView scoreHome;
	private SharedScore sharedScore;
	private int scoreDefault = 0;

	public HomeFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Instansiasi ViewModel SharedScore
		sharedScore = ViewModelProviders.of(requireActivity()).get(SharedScore.class);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		scoreDuaHome = view.findViewById(R.id.scoreDuaHome);
		scoreTigaHome = view.findViewById(R.id.scoreTigaHome);
		scoreHome = view.findViewById(R.id.scoreHome);
		scoreSatuHome = view.findViewById(R.id.scoreSatuHome);

		// Tambahkan logic tombol di bagian bawah ini
		sharedScore.getScoreHome().observe(requireActivity(), new Observer<Integer>() {
			@Override
			public void onChanged(Integer score) {
				scoreHome.setText(String.valueOf(score));
				scoreDefault = score;
			}
		});
		//Observer Pattern memerlukan Publisher.
		scoreDuaHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sharedScore.setScoreHome(scoreDefault + 2);
			}
		});

		scoreTigaHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sharedScore.setScoreHome(scoreDefault + 3);
			}
		});

		scoreSatuHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sharedScore.setScoreHome(scoreDefault + 1);
			}
		});
	}
}
