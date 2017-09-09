package com.example.arushi.hackathon;

/**
 * Created by Arushi on 9/9/2017.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;


import java.util.HashMap;

/**
 * Created by Pallavi on 9/8/17.
 */

public class CharityJSONParser {

    public List<HashMap<String,Object>> parse(JSONObject jObject) {

        JSONArray jCharaties = null;

        try {
            jCharaties = jObject.getJSONArray("payload");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getCharities(jCharaties);
    }

    public List<HashMap<String,Object>> getCharities(JSONArray jCharities) {
        int charitiesCount = jCharities.length();
        List<HashMap<String,Object>> charityList = new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> charity = null;

        for(int i = 0; i < charitiesCount; i++) {
            try {
                charity = getCharity((JSONObject) jCharities.get(i));
                charityList.add(charity);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return charityList;
    }

    private HashMap<String, Object> getCharity(JSONObject jCharity) {
        HashMap<String,Object> charity = new HashMap<String,Object>();
        String charityName = "";
        String address = "";

        try {
            charityName = jCharity.getString("organization_name");
            address = jCharity.getString("address_full");

            charity.put("Charity",charityName);
            charity.put("Address",address);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return charity;


    }
}
