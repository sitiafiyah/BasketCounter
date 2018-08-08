package com.siti.asyst.testcase.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.siti.asyst.testcase.R;
import com.siti.asyst.testcase.utility.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchFragment.OnButtonNextClickedListener} interface
 * to handle interaction events.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int scoreTeama, scoreTeamb;
    Button buttonPlusOneTeamA, buttonPlusTwoTeamA, buttonPlusThreeTeamA;
    Button buttonPlusOneTeamB, buttonPlusTwoTeamB, buttonPlusThreeTeamB;
    Button next;
    TextView quarterTv, teamATV, teamBTV, scoreTeamA, scoreTeamB;
    // TODO: Rename and change types of parameters
    private String teamAName;
    private String teamBName;
    private int quarter;
    private OnButtonNextClickedListener mListener;

    public MatchFragment() {
        // Required empty public constructor
    }

    public static MatchFragment newInstance(String teamA, String teamB, int quarter, int scoreTeama, int scoreTeamb) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        args.putString(Constant.KEY_TEAM_A_NAME, teamA);
        args.putString(Constant.KEY_TEAM_B_NAME, teamB);
        args.putInt(Constant.KEY_QUARTER, quarter);
        args.putInt(Constant.KEY_SCORE_TEAM_A, scoreTeama);
        args.putInt(Constant.KEY_SCORE_TEAM_B, scoreTeamb);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamAName = getArguments().getString(Constant.KEY_TEAM_A_NAME);
            teamBName = getArguments().getString(Constant.KEY_TEAM_B_NAME);
            quarter = getArguments().getInt(Constant.KEY_QUARTER);
            scoreTeama = getArguments().getInt(Constant.KEY_SCORE_TEAM_A);
            scoreTeamb = getArguments().getInt(Constant.KEY_SCORE_TEAM_B);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        quarterTv = view.findViewById(R.id.quarter_textview);
        teamATV = view.findViewById(R.id.teama_textview);
        teamBTV = view.findViewById(R.id.teamb_textview);

        scoreTeamA = view.findViewById(R.id.teama_score_textview);
        scoreTeamB = view.findViewById(R.id.teamb_score_textview);

        buttonPlusOneTeamA = view.findViewById(R.id.plus_one_teama_button);
        buttonPlusTwoTeamA = view.findViewById(R.id.plus_two_teama_button);
        buttonPlusThreeTeamA = view.findViewById(R.id.plus_three_teama_button);

        buttonPlusOneTeamB = view.findViewById(R.id.plus_one_teamb_button);
        buttonPlusTwoTeamB = view.findViewById(R.id.plus_two_teamb_button);
        buttonPlusThreeTeamB = view.findViewById(R.id.plus_three_teamb_button);

        next = view.findViewById(R.id.button_next);

        buttonPlusOneTeamA.setOnClickListener(this);
        buttonPlusTwoTeamA.setOnClickListener(this);
        buttonPlusThreeTeamA.setOnClickListener(this);

        buttonPlusOneTeamB.setOnClickListener(this);
        buttonPlusTwoTeamB.setOnClickListener(this);
        buttonPlusThreeTeamB.setOnClickListener(this);

        next.setOnClickListener(this);

        switch (quarter) {
            case 1:
                quarterTv.setText("1st");
                break;
            case 2:
                quarterTv.setText("2nd");
                break;
            case 3:
                quarterTv.setText("3rd");
                break;
            case 4:
                quarterTv.setText("4rd");
                break;
        }

        teamATV.setText(teamAName);
        teamBTV.setText(teamBName);
        scoreTeamA.setText(scoreTeama + "");
        scoreTeamB.setText(scoreTeamb + "");

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonNextClickedListener) {
            mListener = (OnButtonNextClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() { //saat keluar dari aktivity-nya
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus_one_teama_button:
                scoreTeama += 1;
                break;

            case R.id.plus_two_teama_button:
                scoreTeama += 2;
                break;

            case R.id.plus_three_teama_button:
                scoreTeama += 3;
                break;

            case R.id.plus_one_teamb_button:
                scoreTeamb += 1;
                break;

            case R.id.plus_two_teamb_button:
                scoreTeamb += 2;
                break;

            case R.id.plus_three_teamb_button:
                scoreTeamb += 3;
                break;

            case R.id.button_next:
                //Toast.makeText(getActivity(), "clik", Toast.LENGTH_SHORT).show();
                mListener.onButtonNextClicked(scoreTeama, scoreTeamb);
                getActivity().getSupportFragmentManager().popBackStack();

                break;
        }

        scoreTeamA.setText(scoreTeama + "");
        scoreTeamB.setText(scoreTeamb + "");

    }

    public interface OnButtonNextClickedListener {
        // TODO: Update argument type and name
        void onButtonNextClicked(int scoreTeamA, int scoreTeamB);
    }
}
