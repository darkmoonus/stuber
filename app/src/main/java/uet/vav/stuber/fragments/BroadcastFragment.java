package uet.vav.stuber.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.tokenautocomplete.FilteredArrayAdapter;
import com.tokenautocomplete.TokenCompleteTextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import tokenautocomplete.ContactsCompletionView;
import tokenautocomplete.ProblemField;
import uet.vav.stuber.R;
import uet.vav.stuber.activities.BroadcastingActivity;
import uet.vav.stuber.activities.MainActivity;
import uet.vav.stuber.cores.CoreFragment;
import uet.vav.stuber.utils.Network;

public class BroadcastFragment extends CoreFragment implements TokenCompleteTextView.TokenListener {

    private ContactsCompletionView mCompletionView;
    private ProblemField[] mFields;
    private ArrayAdapter<ProblemField> mTagAdapter;
    private List<ProblemField> mAddedFields;
    private Button mSendBroadCastButton;
    private EditText mProblemEditText;

    @Override
    public void onClick(View view) {
    }

    @Override
    protected void initModels() {
    }

    @Override
    protected void initViews(View v) {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_broadcast, container, false);
        mProblemEditText = (EditText) view.findViewById(R.id.problem_edittext);

        mSendBroadCastButton = (Button) view.findViewById(R.id.send_broadcast_button);
        mSendBroadCastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAddedFields.size() == 0) {
                    mActivity.showProgressDialogWithPositiveButton("error", "You are required to add at least 1 field!");
                } else if (mProblemEditText.getText().length() <= 10) {
                    mActivity.showProgressDialogWithPositiveButton("error", "Your problem must be more than 10 characters!");
                } else {
                    mActivity.showProgressDialog("loading", "Message is broadcasting...");

                    ParseObject broadcastRequest = new ParseObject("Question");
                    broadcastRequest.put("fields", mAddedFields.toString());
                    broadcastRequest.put("question", mProblemEditText.getText().toString());
                    broadcastRequest.put("sender", ParseUser.getCurrentUser().getEmail());

//                    final String questionId = broadcastRequest.getObjectId();

                    broadcastRequest.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            try {
                                JSONObject data = new JSONObject();
                                data.put("fields", mAddedFields.toString());
                                data.put("question", mProblemEditText.getText().toString());
                                data.put("sender", ParseUser.getCurrentUser().getEmail());
                                HttpEntity entity = new StringEntity(data.toString());

                                Network.post(mActivity, "https://api.parse.com/1/functions/getUsersGeoPoint",
                                        entity, new AsyncHttpResponseHandler() {
                                            @Override
                                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                Log.d("onSuccess", new String(responseBody));

                                                mActivity.removePreviousDialog("loading");

                                                Intent intent = new Intent(mContext, BroadcastingActivity.class);
                                                intent.putExtra("data", new String(responseBody));
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                                mActivity.showProgressDialogWithPositiveButton("error", new String(responseBody));
                                                Log.d("onFailure", new String(responseBody));
                                            }
                                        });

                            } catch (Exception ae) {
                                ae.printStackTrace();
                            }


//                            Network.postQuestion("https://api.parse.com/1/functions/getUsersGeoPoint", params,
//                                    new AsyncHttpResponseHandler() {
//                                        @Override
//                                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                                            mActivity.removePreviousDialog("loading");
//
//                                            Intent intent = new Intent(mContext, BroadcastingActivity.class);
//                                            intent.putExtra("data", new String(responseBody));
//                                            startActivity(intent);
//                                        }
//
//                                        @Override
//                                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                                            mActivity.removePreviousDialog("loading");
//                                            mActivity.showProgressDialogWithPositiveButton("error", new String(responseBody));
//                                        }
//                                    });
                        }
                    });
                }
            }
        });

        mAddedFields = new ArrayList<>();
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
                return (tag.getName().toLowerCase().startsWith(mask) && !mAddedFields.contains(tag.getName()));
            }
        };

        mCompletionView = (ContactsCompletionView) view.findViewById(R.id.searchView);
        mCompletionView.setAdapter(mTagAdapter);
        mCompletionView.setTokenListener(this);
        mCompletionView.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Select);

        return view;
    }

    @Override
    protected void initAnimations() {

    }

    @Override
    protected void initListener() {
    }

    public static final long serialVersionUID = 6036846677812555352L;
    public static MainActivity mActivity;
    public static BroadcastFragment mInstance;

    public static CoreFragment getInstance(MainActivity activity) {
        if (mInstance == null) {
            mInstance = new BroadcastFragment();
        }
        mActivity = activity;
        return mInstance;
    }

    @Override
    public void onTokenAdded(Object field) {
        mAddedFields.add(((ProblemField) field));
        Toast.makeText(getActivity().getApplicationContext(), "Number of fields: " + mAddedFields.size(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTokenRemoved(Object field) {
        mAddedFields.remove(field);
    }
}
