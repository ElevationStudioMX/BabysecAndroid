package com.example.web_api_spotify;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;

import org.fest.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import com.example.web_api_spotify.models.Album;
import com.example.web_api_spotify.models.AlbumSimple;
import com.example.web_api_spotify.models.Albums;
import com.example.web_api_spotify.models.AlbumsPager;
import com.example.web_api_spotify.models.Artist;
import com.example.web_api_spotify.models.ArtistSimple;
import com.example.web_api_spotify.models.Artists;
import com.example.web_api_spotify.models.ArtistsPager;
import com.example.web_api_spotify.models.AudioFeaturesTrack;
import com.example.web_api_spotify.models.AudioFeaturesTracks;
import com.example.web_api_spotify.models.CategoriesPager;
import com.example.web_api_spotify.models.Category;
import com.example.web_api_spotify.models.Copyright;
import com.example.web_api_spotify.models.ErrorDetails;
import com.example.web_api_spotify.models.ErrorResponse;
import com.example.web_api_spotify.models.FeaturedPlaylists;
import com.example.web_api_spotify.models.Followers;
import com.example.web_api_spotify.models.Image;
import com.example.web_api_spotify.models.LinkedTrack;
import com.example.web_api_spotify.models.NewReleases;
import com.example.web_api_spotify.models.Playlist;
import com.example.web_api_spotify.models.PlaylistFollowPrivacy;
import com.example.web_api_spotify.models.PlaylistTracksInformation;
import com.example.web_api_spotify.models.PlaylistSimple;
import com.example.web_api_spotify.models.PlaylistTrack;
import com.example.web_api_spotify.models.PlaylistsPager;
import com.example.web_api_spotify.models.Recommendations;
import com.example.web_api_spotify.models.Result;
import com.example.web_api_spotify.models.SavedTrack;
import com.example.web_api_spotify.models.Seed;
import com.example.web_api_spotify.models.SeedsGenres;
import com.example.web_api_spotify.models.SnapshotId;
import com.example.web_api_spotify.models.Track;
import com.example.web_api_spotify.models.TrackSimple;
import com.example.web_api_spotify.models.TracksToRemove;
import com.example.web_api_spotify.models.TracksToRemoveWithPosition;
import com.example.web_api_spotify.models.Tracks;
import com.example.web_api_spotify.models.TracksPager;
import com.example.web_api_spotify.models.TrackToRemove;
import com.example.web_api_spotify.models.TrackToRemoveWithPosition;
import com.example.web_api_spotify.models.UserPublic;
import com.example.web_api_spotify.models.UserPrivate;
/*
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.AlbumSimple;
import kaaes.spotify.webapi.android.models.Albums;
import kaaes.spotify.webapi.android.models.AlbumsPager;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.AudioFeaturesTrack;
import kaaes.spotify.webapi.android.models.AudioFeaturesTracks;
import kaaes.spotify.webapi.android.models.CategoriesPager;
import kaaes.spotify.webapi.android.models.Category;
import kaaes.spotify.webapi.android.models.Copyright;
import kaaes.spotify.webapi.android.models.ErrorDetails;
import kaaes.spotify.webapi.android.models.ErrorResponse;
import kaaes.spotify.webapi.android.models.FeaturedPlaylists;
import kaaes.spotify.webapi.android.models.Followers;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.LinkedTrack;
import kaaes.spotify.webapi.android.models.NewReleases;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistFollowPrivacy;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import kaaes.spotify.webapi.android.models.PlaylistTracksInformation;
import kaaes.spotify.webapi.android.models.PlaylistsPager;
import kaaes.spotify.webapi.android.models.Recommendations;
import kaaes.spotify.webapi.android.models.Result;
import kaaes.spotify.webapi.android.models.SavedTrack;
import kaaes.spotify.webapi.android.models.Seed;
import kaaes.spotify.webapi.android.models.SeedsGenres;
import kaaes.spotify.webapi.android.models.SnapshotId;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TrackSimple;
import kaaes.spotify.webapi.android.models.TrackToRemove;
import kaaes.spotify.webapi.android.models.TrackToRemoveWithPosition;
import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;
import kaaes.spotify.webapi.android.models.TracksToRemove;
import kaaes.spotify.webapi.android.models.TracksToRemoveWithPosition;
import kaaes.spotify.webapi.android.models.UserPrivate;
import kaaes.spotify.webapi.android.models.UserPublic;*/

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ParcelableModelsTest {

    @Test
    public void allParcelables() throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        ModelPopulator populator = new ModelPopulator("CREATOR", "$jacocoData");

        for (Class<? extends Parcelable> modelClass : getModelClasses()) {

            Parcelable instance = populator.populateWithRandomValues(modelClass);

            testSingleParcelable(instance);
            testParcelableArray(instance);

            /* Trick to increase code coverage */
            instance.describeContents();
            ((Parcelable.Creator<?>) modelClass.getField("CREATOR").get(null)).newArray(13);
        }
    }

    ArrayList<Class<? extends Parcelable>> getModelClasses() {
        return Lists.newArrayList(
                Album.class,
                Albums.class,
                AlbumSimple.class,
                AlbumsPager.class,
                Artist.class,
                Artists.class,
                ArtistSimple.class,
                ArtistsPager.class,
                AudioFeaturesTrack.class,
                AudioFeaturesTracks.class,
                CategoriesPager.class,
                Category.class,
                Copyright.class,
                ErrorDetails.class,
                ErrorResponse.class,
                FeaturedPlaylists.class,
                Followers.class,
                Image.class,
                LinkedTrack.class,
                NewReleases.class,
                Playlist.class,
                PlaylistFollowPrivacy.class,
                PlaylistSimple.class,
                PlaylistsPager.class,
                PlaylistTrack.class,
                PlaylistTracksInformation.class,
                Recommendations.class,
                Result.class,
                Seed.class,
                SeedsGenres.class,
                SavedTrack.class,
                SnapshotId.class,
                Track.class,
                Tracks.class,
                TrackSimple.class,
                TracksPager.class,
                TrackToRemove.class,
                TrackToRemoveWithPosition.class,
                TracksToRemove.class,
                TracksToRemoveWithPosition.class,
                UserPrivate.class,
                UserPublic.class
        );
    }

    <T extends Parcelable> void testSingleParcelable(T underTest) {

        Parcel parcel = Parcel.obtain();
        parcel.writeParcelable(underTest, 0);
        parcel.setDataPosition(0);
        T fromParcel = parcel.readParcelable(underTest.getClass().getClassLoader());

        ModelAssert.assertThat(fromParcel).isEqualByComparingFields(underTest);
    }

    <T extends Parcelable> void testParcelableArray(T underTest) {

        Parcel parcel = Parcel.obtain();
        parcel.writeParcelableArray(Arrays.array(underTest, underTest), 0);
        parcel.setDataPosition(0);
        Parcelable[] fromParcel = parcel.readParcelableArray(underTest.getClass().getClassLoader());

        assertThat(fromParcel).hasSize(2);
        assertThat(fromParcel[0]).isEqualsToByComparingFields(underTest);
        assertThat(fromParcel[1]).isEqualsToByComparingFields(underTest);
    }

    @Test
    public void artistsAreGoodParcelableCitizen() {
        String body = TestUtils.readTestData("artists.json");
        Artists fixture = new GsonBuilder().create().fromJson(body, Artists.class);
        testSingleParcelable(fixture);
    }

    @Test
    public void albumsAreGoodParcelableCitizen() {
        String body = TestUtils.readTestData("albums.json");
        Albums fixture = new GsonBuilder().create().fromJson(body, Albums.class);
        testSingleParcelable(fixture);
    }

    @Test
    public void tracksAreGoodParcelableCitizen() {
        String body = TestUtils.readTestData("tracks.json");
        Tracks fixture = new GsonBuilder().create().fromJson(body, Tracks.class);
        testSingleParcelable(fixture);
    }

    @Test
    public void usersAreGoodParcelableCitizens() {
        String body = TestUtils.readTestData("user.json");
        UserPublic userPublic = new GsonBuilder().create().fromJson(body, UserPublic.class);
        testSingleParcelable(userPublic);

        body = TestUtils.readTestData("current-user.json");
        UserPrivate userPrivate = new GsonBuilder().create().fromJson(body, UserPrivate.class);
        testSingleParcelable(userPrivate);
    }

}