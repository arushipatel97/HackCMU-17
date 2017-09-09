package com.example.arushi.hackathon;


        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;

        import java.util.ArrayList;

/**
 * Created by Pallavi on 9/9/17.
 */

public class PopulateClubList extends edit {

    ListView mListView;
    ArrayList<Club> clubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populate_club_list2);

        clubs = new ArrayList<Club>();

        Club Jiya = new Club("https://cmujiya.files.wordpress.com/2016/08/final1.png?w=415",
                "Jiya");
        Club SWSG = new Club("http://files.constantcontact.com/e2ba8c26001/28d0b8d5-bc8b-47bf-" +
                "a2ec-5a96c40a97d2.png?a=1126753405113", "Strong Women Strong Girls");
        Club WinECE = new Club("https://www.ece.cmu.edu/_media/news-images/2001/05/winecelogo.gif",
                "WinECE");
        Club SWE = new Club("https://regiong.files.wordpress.com/2015/02/swelogo-proposed-" +
                "regiong.jpg","Society of Women Engineers");
        Club APhiO = new Club("http://static.tumblr.com/97450229e519966c86da6635eae076b2/kgds3vf" +
                "/vY1niwux3/tumblr_static_262yy3qq2kkkso8sc4wooc8g4.jpg","Alpha Phi Omega");
        Club ACM = new Club("http://acmatcmu.org/images/title.png?1472151521", "ACM@CMU");

        clubs.add(ACM);
        clubs.add(Jiya);
        clubs.add(SWSG);
        clubs.add(WinECE);
        clubs.add(SWE);
        clubs.add(APhiO);

        CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(),
                R.layout.activity_custom_list_adapter,clubs);

        mListView = (ListView) findViewById(R.id.lv_clubs);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long l) {
                Club click = (Club)
                        mListView.getItemAtPosition(position);
                clicked = click.getName();

            }
        });

    }
}