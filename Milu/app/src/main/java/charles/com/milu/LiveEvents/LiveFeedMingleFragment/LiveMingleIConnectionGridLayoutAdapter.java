/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package charles.com.milu.LiveEvents.LiveFeedMingleFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.Items.RecyclingImageView;
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.R;
import charles.com.milu.Utility.SquareImageView;
import charles.com.milu.utils.logger.Images;
import ooo.oxo.library.widget.TouchImageView;


public class LiveMingleIConnectionGridLayoutAdapter extends RecyclerView.Adapter<LiveMingleIConnectionGridLayoutAdapter.SimpleViewHolder> implements LiveFeedConnectionAdapter.ItemClickListener {
    private String[] userFullNames = {
            "Alvaro Alcocer","Alberto Ates", "Keven Kujawa", "Weldon Wickman",    "Dion Dockstader",    "Domingo Demming",    "Vito Vizcarrondo",    "Ellsworth Edelson",
            "Trey Torgeson",    "Morton Mendoza",    "Tyler Twyman",    "Eric Enriguez",    "Juan Judon",    "Alfred Arellano",    "Garret Ganley",    "Ryan Rotolo",
            "Thanh Thor",    "Clint Cumberland",    "Arlie Abate",    "Emil Eaddy",    "Dwayne Dermody",    "Arron Arrigo",    "Cedric Cavender",
            "Elmo Edelen",    "Devin Dumais",    "Stewart Seaberg",    "Grover Gioia",    "Jamey Junkins",    "Marcellus Marchan",    "Cruz Choudhury",
            "Virgilio Valls",    "Jed Jules",    "Ross Race",    "Salvatore Sarcone",    "Lupe Loeb",    "Ernie Enger",    "Len Laven",    "Paul Potter",
            "Adan Abarca",    "Percy Pando",    "Dusty Dombrowski",    "Gilbert Gershman",    "Jarred Jaqua",    "Carrol Capone",    "Shon Surprenant",    "Ellis Eacret",
            "Elijah Eggert",    "Leonard Lamica",    "Kris Kleiber",    "Arden An",    "Ian Issac",    "Genaro Geter",   "Julianna Jerrell",   "Mahalia Marchi",
            "Le Lerma",            "Marjory Merryman",            "Luigi Laporte",            "Beata Bilski",           "Dominick Duhn",            "Brooks Bettencourt",
            "Onie Oyola",            "Quintin Quinn",            "Jill Jasinski",            "Arlen Alm",            "Lashawnda Lisa",            "Tona Toye",
            "Dorthy Devereux",            "Deb Driscoll",            "Miesha Mundy",            "Kelvin Kinsman",            "Florine Franzone",            "Rosia Rudy",
            "Temeka Tuff",            "Felipe Fein",            "Brigitte Batdorf",            "Lilly Londono",            "Deloras Dempster",            "Iluminada Ikeda",
            "Nidia Nicks",            "Matt Mccoy",            "Susannah Strebel",            "Tanya Tardiff",            "Raul Robeson",            "Claretha Canale",
            "Mireya Minnich",            "Shameka Smidt",            "Lakeesha Lenzi",            "Vito Vance",            "Catharine Ciampa",            "Enoch Estevez",
            "Eden Embry",            "Shawnta Scoles",            "Cristine Chavis",            "Ines Immel",            "Bell Bass",            "Evangelina Epps",
            "Fletcher Fuentez",            "Senaida Swager",            "Buck Byrns",            "Sharleen Smead",            "Yong Yarbro",            "Monnie Mcgrane",
            "Donita Dubray",            "Deandre Delaughter",            "Lorilee Lydick",            "Bob Bergen",            "Kittie Kao",            "Margie Marley",
            "Lu Lammers",            "Clara Carlos",            "Rachal Rozzi",            "Warren Willimas",            "Adrien Asbury",            "Lilian Lovely",
            "Ernestine Eisner",            "Minna Milian",            "Lecia Lamica",            "Kristie Kerry",            "Thomasena Tedeschi",            "Carlita Corella",
            "Ivelisse Ishee",            "Kati Kloster",            "Obdulia Onstad",            "Marisela Mcconnel",            "Damon Delzell",            "Selina Seim",
            "Librada Luellen",            "Marylynn Mattie",            "Dora Debolt",            "Louis Lueck",            "Camellia Chun",            "Karen Kissinger",
            "Annamarie Apple",            "Mazie Mccausland",            "Belinda Bodin",            "Shelli Sanluis",            "Rochell Rafael",            "Hedwig Hildebrandt",            "Millicent Marie",            "Winnie Womack",            "Almeta Abundis",
            "Susan Searls",            "Gus Grisby",            "Paul Penix",            "Emogene Elwell",            "Misti Mccolley",            "Hugo Harpster",
            "Grady Gunnell",            "Sana Shumate",            "Loraine Leff"};

    private static final int DEFAULT_ITEM_COUNT = 3;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private final List<Integer> mItems;
    ArrayList<LiveFeedConnectionItem> mConnectionItems;
    private int mCurrentItemId = 0;

    @Override
    public void connectionItemClicked(View v, int adapterPosition) {

    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView gridView;


        public SimpleViewHolder(View view) {
            super(view);
            gridView = (RecyclerView) view.findViewById(R.id.connection_grid_view);
        }
    }

    public LiveMingleIConnectionGridLayoutAdapter(Context context, RecyclerView recyclerView) {
       this(context, recyclerView, DEFAULT_ITEM_COUNT);
    }

    public LiveMingleIConnectionGridLayoutAdapter(Context context, RecyclerView recyclerView, int itemCount) {
        mContext = context;
        mItems = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            addItem(i);
        }

        mRecyclerView = recyclerView;
    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.livemingle_connection_gridview, parent, false);
        return new SimpleViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {

        final View itemView = holder.itemView;
        RecyclerView recyclerView = holder.gridView;
        final int itemId = mItems.get(position);

        mConnectionItems = LiveFeedConnectionItem.createContactsList(0);
        mConnectionItems.add(0, new LiveFeedConnectionItem(R.drawable.movements,"movements","1"));
        mConnectionItems.add(1, new LiveFeedConnectionItem(R.drawable.outdoor_adventure,"outdoor & adventure","1"));
        mConnectionItems.add(2, new LiveFeedConnectionItem(R.drawable.tech,"tech","1"));
        mConnectionItems.add(3, new LiveFeedConnectionItem(R.drawable.family,"family","1"));
        mConnectionItems.add(4, new LiveFeedConnectionItem(R.drawable.health_wellness,"health & wellness","1"));
        mConnectionItems.add(5, new LiveFeedConnectionItem(R.drawable.sports_fitness,"sports & fitness","1"));


        LiveFeedConnectionAdapter adapter = new LiveFeedConnectionAdapter(mContext, mConnectionItems);
//        adapter.setListener(mContext);
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 20, true));

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
