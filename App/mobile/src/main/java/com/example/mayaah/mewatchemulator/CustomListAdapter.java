package com.example.mayaah.mewatchemulator;

/**
 * Created by mayaah on 2/29/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;
    private final String[] itemparty;
    private final String[] itememail;
    private final String[] itemwebsite;
    private final String[] itemtweet;


    public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid, String[] itemparty, String[] itememail, String[] itemwebsite, String[] itemtweet) {
        super(context, R.layout.representative_list, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.itemparty = itemparty;
        this.itememail = itememail;
        this.itemwebsite = itemwebsite;
        this.itemtweet = itemtweet;
    }

    public View getView(final int position,View view,ViewGroup parent) {
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Light.ttf");

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.representative_list, null, true);

        TextView nameTxt = (TextView) rowView.findViewById(R.id.name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView partyTxt = (TextView) rowView.findViewById(R.id.party);
//        TextView emailTxt = (TextView) rowView.findViewById(R.id.email);
//        TextView websiteTxt = (TextView) rowView.findViewById(R.id.website);
        TextView tweetTxt = (TextView) rowView.findViewById(R.id.tweet);
        ImageView websiteImg = (ImageView) rowView.findViewById(R.id.siteView);
        ImageView emailImg = (ImageView) rowView.findViewById(R.id.emailView);

        nameTxt.setText(itemname[position]);
        nameTxt.setTypeface(custom_font);
//        imageView.setImageResource(imgid[position]);
//        Toast.makeText(getContext(), itemparty[position], Toast.LENGTH_SHORT).show();
        if (itemparty[position].equals("D")) {
            partyTxt.setText("Democrat");
        } else {
            partyTxt.setText("Republican");
        }

        partyTxt.setTypeface(custom_font);
        websiteImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(itemwebsite[position]));
                context.startActivity(intent);
            }
        });
        emailImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.fromParts("mailto",itememail[position], null));
                context.startActivity(i);
            }
        });
//        emailTxt.setText(itememail[position]);
//        emailTxt.setTypeface(custom_font);
//        websiteTxt.setText(itemwebsite[position]);
//        websiteTxt.setTypeface(custom_font);
//        tweetTxt.setText(itemtweet[position]);

        return rowView;

    };
}
