package uet.vav.stuber.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tokenautocomplete.FilteredArrayAdapter;
import com.tokenautocomplete.TokenCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import tokenautocomplete.ContactsCompletionView;
import tokenautocomplete.ProblemField;
import uet.vav.stuber.R;
import uet.vav.stuber.cores.CoreFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BroadcastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BroadcastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BroadcastFragment extends CoreFragment implements TokenCompleteTextView.TokenListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ContactsCompletionView mCompletionView;
    private ProblemField[] mFields;
    private ArrayAdapter<ProblemField> mTagAdapter;
    private List<String> mOfferedTags;

    public BroadcastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BroadcastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BroadcastFragment newInstance(String param1, String param2) {
        BroadcastFragment fragment = new BroadcastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_broadcast, container, false);

        mOfferedTags = new ArrayList<>();
        mFields = new ProblemField[]{
                new ProblemField(1, "Java"),
                new ProblemField(2, "Android"),
                new ProblemField(3, "PHP"),
                new ProblemField(4, "Javascript"),
                new ProblemField(5, "Python"),
                new ProblemField(6, "Web"),
                new ProblemField(7, "AngularJS"),
                new ProblemField(8, "Mathematics"),
                new ProblemField(9, "C/C++"),
                new ProblemField(10, "Physics"),
                new ProblemField(11, "Calculus"),
                new ProblemField(12, "Chemistry")
        };

        mTagAdapter = new FilteredArrayAdapter<ProblemField>(view.getContext(), R.layout.item_tag, mFields) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {

                    LayoutInflater l = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    convertView = l.inflate(R.layout.item_tag, parent, false);
                }

                ProblemField p = getItem(position);
                ((TextView) convertView.findViewById(R.id.name)).setText(p.getName());

                return convertView;
            }

            @Override
            protected boolean keepObject(ProblemField tag, String mask) {
                mask = mask.toLowerCase();
                return (tag.getName().toLowerCase().startsWith(mask) && !mOfferedTags.contains(tag.getName()));
            }
        };

        mCompletionView = (ContactsCompletionView) view.findViewById(R.id.searchView);
        mCompletionView.setAdapter(mTagAdapter);
        mCompletionView.setTokenListener(this);
        mCompletionView.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Select);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onTokenAdded(Object token) {
        mOfferedTags.add(((ProblemField) token).getName());
    }

    @Override
    public void onTokenRemoved(Object token) {

    }
}
