package com.example.podcastapp;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable {
    @FXML
    private Button NextButton, PlayButton, PrevButton, ReplayButton, shuffleButton, logOutButton, garyVeeButton, RSMButton, MIButton, JBButton, PrevArrowButton, NextArrowButton, topOne, topTwo, topThree, topFour, topFive;
    @FXML
    private Label casterName, podcastTitle, currentTime, endTime, labelVolume, searchLabel, homeLabel, logOutLabel, RSMTitle, profileLabel, podcastImage, categoriesLabel, favoritesLabel, uploadsLabel, appLogo;
    @FXML
    private Slider volumeSlider, progress;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;
    private ArrayList<File> podcasts;
    private int podcastNumber = 0;
    private boolean atEndOfPodcast = false, isPlaying = true, isMuted = true;
    private ImageView searchIcon, ivPlay, ivPause, ivShuffle, ivPrev, ivNext, ivReplay, ivMute, ivVolume, trendingPodcastImage, homeIcon, logOutIcon, ivRSM ,ivMI, ivJB, ivArrowNext, ivArrowPrev, ivGV, ivAT, ivPM, ivJR, ivPBD, ivProfile, ivPodcast, ivCategories, ivFavorites, ivUploads, ivLogo;
    Rectangle rectangle, rectangle1, rectangle2, rectangle3, circle1, circle2, circle3, circle4, circle5, circle6;
    String tempString;
    String[] splitString;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Setting the icons
        Image logo = new Image(new File("src/assets/podcast.png").toURI().toString());
        ivLogo = new ImageView(logo);
        ivLogo.setFitHeight(18);
        ivLogo.setFitWidth(18);
        appLogo.setGraphic(ivLogo);

        circle1 = new Rectangle(0, 0, 32, 32);
        circle1.setArcWidth(32);
        circle1.setArcHeight(32);
        Image GVImage = new Image(new File("src/assets/GV.jpg").toURI().toString());
        ivGV = new ImageView(GVImage);
        ivGV.setFitWidth(32);
        ivGV.setFitHeight(32);
        ivGV.setClip(circle1);
        topOne.setGraphic(ivGV);

        circle2 = new Rectangle(0, 0, 32, 32);
        circle2.setArcWidth(32);
        circle2.setArcHeight(32);
        Image ATIMage = new Image(new File("src/assets/andrewTate.jpg").toURI().toString());
        ivAT = new ImageView(ATIMage);
        ivAT.setFitWidth(32);
        ivAT.setFitHeight(32);
        ivAT.setClip(circle2);
        topTwo.setGraphic(ivAT);

        circle3 = new Rectangle(0, 0, 32, 32);
        circle3.setArcWidth(32);
        circle3.setArcHeight(32);
        Image JRIMage = new Image(new File("src/assets/joeRogan.jpg").toURI().toString());
        ivJR = new ImageView(JRIMage);
        ivJR.setFitWidth(32);
        ivJR.setFitHeight(32);
        ivJR.setClip(circle3);
        topThree.setGraphic(ivJR);

        circle4 = new Rectangle(0, 0, 32, 32);
        circle4.setArcWidth(32);
        circle4.setArcHeight(32);
        Image PMIMage = new Image(new File("src/assets/piersMorgan.jpg").toURI().toString());
        ivPM = new ImageView(PMIMage);
        ivPM.setFitWidth(32);
        ivPM.setFitHeight(32);
        ivPM.setClip(circle4);
        topFour.setGraphic(ivPM);

        circle5 = new Rectangle(0, 0, 32, 32);
        circle5.setArcWidth(32);
        circle5.setArcHeight(32);
        Image PBDIMage = new Image(new File("src/assets/PBD.jpg").toURI().toString());
        ivPBD = new ImageView(PBDIMage);
        ivPBD.setFitWidth(32);
        ivPBD.setFitHeight(32);
        ivPBD.setClip(circle5);
        topFive.setGraphic(ivPBD);

        circle6 = new Rectangle(0, 0, 32, 32);
        circle6.setArcWidth(32);
        circle6.setArcHeight(32);
        Image PImage = new Image(new File("src/assets/profilePic.jpg").toURI().toString());
        ivProfile = new ImageView(PImage);
        ivProfile.setFitHeight(32);
        ivProfile.setFitWidth(32);
        ivProfile.setClip(circle6);
        profileLabel.setGraphic(ivProfile);

        RSMTitle.setText("Resist the Slave\nMind");
        Image loIcon = new Image(new File("src/assets/logout.png").toURI().toString());
        logOutIcon = new ImageView(loIcon);
        logOutIcon.setFitWidth(12);
        logOutIcon.setFitHeight(12);
        logOutLabel.setGraphic(logOutIcon);

        Image hIcon = new Image(new File("src/assets/homeIconSmall.png").toURI().toString());
        homeIcon = new ImageView(hIcon);
        homeIcon.setFitWidth(12);
        homeIcon.setFitHeight(12);
        homeLabel.setGraphic(homeIcon);

        Image categoryIcon = new Image(new File("src/assets/categories.png").toURI().toString());
        ivCategories = new ImageView(categoryIcon);
        ivCategories.setFitWidth(12);
        ivCategories.setFitHeight(12);
        categoriesLabel.setGraphic(ivCategories);

        Image favoritesIcon = new Image(new File("src/assets/bookmark.png").toURI().toString());
        ivFavorites = new ImageView(favoritesIcon);
        ivFavorites.setFitWidth(12);
        ivFavorites.setFitHeight(12);
        favoritesLabel.setGraphic(ivFavorites);

        Image uploadsIcon = new Image(new File("src/assets/cloud-computing.png").toURI().toString());
        ivUploads = new ImageView(uploadsIcon);
        ivUploads.setFitWidth(12);
        ivUploads.setFitHeight(12);
        uploadsLabel.setGraphic(ivUploads);

        rectangle = new Rectangle(0, 0, 300, 150);
        rectangle.setArcHeight(32);
        rectangle.setArcWidth(32);
        Image imagePodcast = new Image(new File("src/assets/gary-vee.jpg").toURI().toString());
        trendingPodcastImage = new ImageView(imagePodcast);
        trendingPodcastImage.setFitWidth(300);
        trendingPodcastImage.setFitHeight(150);
        trendingPodcastImage.setClip(rectangle);
        garyVeeButton.setGraphic(trendingPodcastImage);

        rectangle1 = new Rectangle(0, 0, 70, 70);
        rectangle1.setArcWidth(32);
        rectangle1.setArcHeight(32);
        Image RSMImage = new Image(new File("src/assets/resistTheSlaveMind.jpg").toURI().toString());
        ivRSM = new ImageView(RSMImage);
        ivRSM.setFitHeight(70);
        ivRSM.setFitWidth(70);
        ivRSM.setClip(rectangle1);
        RSMButton.setGraphic(ivRSM);

        rectangle2 = new Rectangle(0, 0, 70, 70);
        rectangle2.setArcHeight(32);
        rectangle2.setArcWidth(32);
        Image MIImage = new Image(new File("src/assets/millennialInvesting.jpg").toURI().toString());
        ivMI = new ImageView(MIImage);
        ivMI.setFitWidth(70);
        ivMI.setFitHeight(70);
        ivMI.setClip(rectangle2);
        MIButton.setGraphic(ivMI);

        rectangle3 = new Rectangle(0, 0, 70, 70);
        rectangle3.setArcHeight(32);
        rectangle3.setArcWidth(32);
        Image JBImage = new Image(new File("src/assets/just-branding.jpg").toURI().toString());
        ivJB = new ImageView(JBImage);
        ivJB.setFitWidth(70);
        ivJB.setFitHeight(70);
        ivJB.setClip(rectangle3);
        JBButton.setGraphic(ivJB);

        Image IANext = new Image(new File("src/assets/nextArrow.png").toURI().toString());
        ivArrowNext = new ImageView(IANext);
        ivArrowNext.setFitHeight(24);
        ivArrowNext.setFitWidth(24);
        NextArrowButton.setGraphic(ivArrowNext);

        Image IAPrev = new Image(new File("src/assets/previousArrow.png").toURI().toString());
        ivArrowPrev = new ImageView(IAPrev);
        ivArrowPrev.setFitHeight(24);
        ivArrowPrev.setFitWidth(24);
        PrevArrowButton.setGraphic(ivArrowPrev);

        Image sIcon = new Image(new File("src/assets/search.png").toURI().toString());
        searchIcon = new ImageView(sIcon);
        searchIcon.setFitHeight(12);
        searchIcon.setFitWidth(12);
        searchLabel.setGraphic(searchIcon);

        Image imagePlay = new Image(new File("src/assets/playButton.png").toURI().toString());
        ivPlay = new ImageView(imagePlay);
        ivPlay.setFitHeight(32);
        ivPlay.setFitWidth(32);

        Image imagePause = new Image(new File("src/assets/pauseButton.png").toURI().toString());
        ivPause = new ImageView(imagePause);
        ivPause.setFitHeight(32);
        ivPause.setFitWidth(32);

        Image imageShuffle = new Image(new File("src/assets/shuffleButton.png").toURI().toString());
        ivShuffle = new ImageView(imageShuffle);
        ivShuffle.setFitHeight(20);
        ivShuffle.setFitWidth(20);

        Image imagePrev = new Image(new File("src/assets/previousPrevious.png").toURI().toString());
        ivPrev = new ImageView(imagePrev);
        ivPrev.setFitHeight(24);
        ivPrev.setFitWidth(24);

        Image imageNext = new Image(new File("src/assets/next.png").toURI().toString());
        ivNext = new ImageView(imageNext);
        ivNext.setFitHeight(24);
        ivNext.setFitWidth(24);

        Image imageRep = new Image(new File("src/assets/replay.png").toURI().toString());
        ivReplay = new ImageView(imageRep);
        ivReplay.setFitHeight(20);
        ivReplay.setFitWidth(20);

        Image imageMute = new Image(new File("src/assets/volume-mute.png").toURI().toString());
        ivMute = new ImageView(imageMute);
        ivMute.setFitHeight(20);
        ivMute.setFitWidth(20);

        Image imageVolume = new Image(new File("src/assets/volumeButton.png").toURI().toString());
        ivVolume = new ImageView(imageVolume);
        ivVolume.setFitHeight(20);
        ivVolume.setFitWidth(20);

        // Set graphic for buttons
        PlayButton.setGraphic(ivPlay);
        labelVolume.setGraphic(ivVolume);
        ReplayButton.setGraphic(ivReplay);
        NextButton.setGraphic(ivNext);
        PrevButton.setGraphic(ivPrev);
        shuffleButton.setGraphic(ivShuffle);

        // Code to get mp3 files from a directory
        podcasts = new ArrayList<>();
        directory = new File("src/garyVee");
        files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                podcasts.add(file);
            }
        }
        media = new Media(podcasts.get(podcastNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        tempString = podcasts.get(podcastNumber).getName();
        splitString = tempString.split(",");
        podcastTitle.setText(splitString[0]);
        casterName.setText(splitString[1]);
        Image IPodcast = new Image(new File("src/assets/gary-vee.jpg").toURI().toString());
        ivPodcast = new ImageView(IPodcast);
        ivPodcast.setFitWidth(50);
        ivPodcast.setFitHeight(50);
        podcastImage.setGraphic(ivPodcast);

        RSMButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.stop();
                podcasts = new ArrayList<>();
                directory = new File("src/andrewTate");
                files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        podcasts.add(file);
                    }
                }
                media = new Media(podcasts.get(podcastNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                tempString = podcasts.get(podcastNumber).getName();
                splitString = tempString.split(",");
                podcastTitle.setText(splitString[0]);
                casterName.setText(splitString[1]);
                PlayButton.setGraphic(ivPlay);
                mediaPlayer.play();
                updateEndTime();
                updateProgress();
                isPlaying = true;
                Image IPodcast = new Image(new File("src/assets/resistTheSlaveMind.jpg").toURI().toString());
                ivPodcast = new ImageView(IPodcast);
                ivPodcast.setFitWidth(50);
                ivPodcast.setFitHeight(50);
                podcastImage.setGraphic(ivPodcast);
            }
        });

        JBButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.stop();
                podcasts = new ArrayList<>();
                directory = new File("src/justBranding");
                files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        podcasts.add(file);
                    }
                }
                media = new Media(podcasts.get(podcastNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                tempString = podcasts.get(podcastNumber).getName();
                splitString = tempString.split(",");
                podcastTitle.setText(splitString[0]);
                casterName.setText(splitString[1]);
                PlayButton.setGraphic(ivPlay);
                mediaPlayer.play();
                updateEndTime();
                updateProgress();
                isPlaying = true;
                Image IPodcast = new Image(new File("src/assets/just-branding.jpg").toURI().toString());
                ivPodcast = new ImageView(IPodcast);
                ivPodcast.setFitWidth(50);
                ivPodcast.setFitHeight(50);
                podcastImage.setGraphic(ivPodcast);
            }
        });
        topTwo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.stop();
                podcasts = new ArrayList<>();
                directory = new File("src/andrewTate");
                files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        podcasts.add(file);
                    }
                }
                media = new Media(podcasts.get(podcastNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                tempString = podcasts.get(podcastNumber).getName();
                splitString = tempString.split(",");
                podcastTitle.setText(splitString[0]);
                casterName.setText(splitString[1]);
                PlayButton.setGraphic(ivPlay);
                mediaPlayer.play();
                updateEndTime();
                updateProgress();
                isPlaying = true;
                Image IPodcast = new Image(new File("src/assets/resistTheSlaveMind.jpg").toURI().toString());
                ivPodcast = new ImageView(IPodcast);
                ivPodcast.setFitWidth(50);
                ivPodcast.setFitHeight(50);
                podcastImage.setGraphic(ivPodcast);
            }
        });

        topOne.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.stop();
                podcasts = new ArrayList<>();
                directory = new File("src/garyVee");
                files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        podcasts.add(file);
                    }
                }
                media = new Media(podcasts.get(podcastNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                tempString = podcasts.get(podcastNumber).getName();
                splitString = tempString.split(",");
                podcastTitle.setText(splitString[0]);
                casterName.setText(splitString[1]);
                PlayButton.setGraphic(ivPlay);
                mediaPlayer.play();
                updateEndTime();
                updateProgress();
                isPlaying = true;
                Image IPodcast = new Image(new File("src/assets/gary-vee.jpg").toURI().toString());
                ivPodcast = new ImageView(IPodcast);
                ivPodcast.setFitWidth(50);
                ivPodcast.setFitHeight(50);
                podcastImage.setGraphic(ivPodcast);
            }
        });
        garyVeeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.stop();
                podcasts = new ArrayList<>();
                directory = new File("src/garyVee");
                files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        podcasts.add(file);
                    }
                }
                media = new Media(podcasts.get(podcastNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                tempString = podcasts.get(podcastNumber).getName();
                splitString = tempString.split(",");
                podcastTitle.setText(splitString[0]);
                casterName.setText(splitString[1]);
                PlayButton.setGraphic(ivPlay);
                mediaPlayer.play();
                updateEndTime();
                updateProgress();
                isPlaying = true;
                Image IPodcast = new Image(new File("src/assets/gary-vee.jpg").toURI().toString());
                ivPodcast = new ImageView(IPodcast);
                ivPodcast.setFitWidth(50);
                ivPodcast.setFitHeight(50);
                podcastImage.setGraphic(ivPodcast);
            }
        });

        // Code to switch between mute and unMute the volume
        labelVolume.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isMuted) {
                    labelVolume.setGraphic(ivVolume);
                    volumeSlider.setValue(50);
                    isMuted = false;
                } else {
                    labelVolume.setGraphic(ivMute);
                    volumeSlider.setValue(0);
                    isMuted = true;
                }
            }
        });

        // Code to get the volume when the slider value changes
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
                if (mediaPlayer.getVolume() != 0.0) {
                    labelVolume.setGraphic(ivVolume);
                    isMuted = false;
                } else {
                    labelVolume.setGraphic(ivMute);
                    isMuted = true;
                }
            }
        });
        //bindCurrentTimeLabel();

        // Code to set the time for the labels and the slider time
        mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldDuration, Duration newDuration) {
                bindCurrentTimeLabel();
                progress.setMax(newDuration.toSeconds());
                endTime.setText(getTime(newDuration));
            }
        });

        // Code to change currentTime depending on the value of the slider
        progress.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean isChanging) {
                bindCurrentTimeLabel();
                if (!isChanging) {
                    mediaPlayer.seek(Duration.seconds(progress.getValue()));
                }
            }
        });

        // Check if we are at the end of the media
        progress.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                bindCurrentTimeLabel();
                double currTime = mediaPlayer.getCurrentTime().toSeconds();
                if (Math.abs(currTime - newValue.doubleValue()) > 0.5) {
                    mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                }
                labelMatchEndOfMedia(currentTime.getText(), endTime.getText());
            }
        });

        // Get the current time
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldTime, Duration newTime) {
                if (!progress.isValueChanging()) {
                    progress.setValue(newTime.toSeconds());
                }
                labelMatchEndOfMedia(currentTime.getText(), endTime.getText());
            }
        });

        // At end of media
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                PlayButton.setGraphic(ivPlay);
                atEndOfPodcast = true;
                if (!currentTime.textProperty().equals(endTime.textProperty())) {
                    currentTime.textProperty().unbind();
                    currentTime.setText(getTime(mediaPlayer.getTotalDuration()));
                }
            }
        });
    }
    void updateProgress() {
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldTime, Duration newTime) {
                if (!progress.isValueChanging()) {
                    progress.setValue(newTime.toSeconds());
                }
                labelMatchEndOfMedia(currentTime.getText(), endTime.getText());
            }
        });
    }
    void updateEndTime() {
        mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldDuration, Duration newDuration) {
                bindCurrentTimeLabel();
                progress.setMax(newDuration.toSeconds());
                endTime.setText(getTime(newDuration));
            }
        });
    }
    void labelMatchEndOfMedia(String currentTime, String endTime) {
        for (int i = 0; i < endTime.length(); i++) {
            if (currentTime.charAt(i) != endTime.charAt(i)) {
                atEndOfPodcast = false;
                if (isPlaying) {
                    PlayButton.setGraphic(ivPause);
                } else {
                    PlayButton.setGraphic(ivPlay);
                }
            } else {
                PlayButton.setGraphic(ivPlay);
                atEndOfPodcast = true;
            }

        }
    }
    void bindCurrentTimeLabel() {
        currentTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getCurrentTime());
            }
        }, mediaPlayer.currentTimeProperty()));
    }

    String getTime(Duration time) {
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();
        if (seconds > 59 ) {
            seconds = seconds % 60;
        }
        if (minutes > 59) {
            minutes = minutes % 60;
        }
        if (hours > 59) {
            hours = hours % 60;
        }

        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        }
        else
            return String.format("%02d:%02d", minutes, seconds);
    }
    @FXML
    void NextMedia(ActionEvent event) {
        if (podcastNumber < podcasts.size() - 1) {
            podcastNumber++;
        } else {
            podcastNumber = 0;
        }
        mediaPlayer.stop();
        progress.setValue(0);
        if (isPlaying) {
            isPlaying = false;
        }
        media = new Media(podcasts.get(podcastNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        podcastTitle.setText(podcasts.get(podcastNumber).getName());
        updateEndTime();
        updateProgress();
        PlayMedia(event);
    }

    @FXML
    void PlayMedia(ActionEvent event) {
        if (atEndOfPodcast) {
            progress.setValue(0);
            PlayButton.setGraphic(ivPlay);
            atEndOfPodcast = false;
            isPlaying = false;
        }
        if (isPlaying) {
            PlayButton.setGraphic(ivPlay);
            mediaPlayer.pause();
            isPlaying = false;
        } else {
            PlayButton.setGraphic(ivPause);
            mediaPlayer.play();
            updateEndTime();
            updateProgress();
            isPlaying = true;
        }
    }
    @FXML
    void ReplayMedia(ActionEvent event) {
        resetMedia();
    }
    void resetMedia() {
        mediaPlayer.seek(Duration.seconds(0.0));
        updateEndTime();
        updateProgress();
        progress.setValue(0);
    }

    @FXML
    void PrevMedia(ActionEvent event) {
        if (podcastNumber > 0) {
            podcastNumber--;
        } else {
            podcastNumber = podcasts.size() - 1;
        }
        mediaPlayer.stop();
        progress.setValue(0);
        if (isPlaying) {
            isPlaying = false;
        }
        media = new Media(podcasts.get(podcastNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        podcastTitle.setText(podcasts.get(podcastNumber).getName());
        updateEndTime();
        updateProgress();
        PlayMedia(event);
    }
    @FXML
    void ShuffleMedia(ActionEvent event) {
        int n = (int) (Math.random() * podcasts.size());
        mediaPlayer.stop();
        progress.setValue(0);
        if (isPlaying) {
            isPlaying = false;
        }
        media = new Media(podcasts.get(n).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        podcastTitle.setText(podcasts.get(n).getName());
        updateEndTime();
        updateProgress();
        PlayMedia(event);
    }
    @FXML
    void toSignInPage(ActionEvent event) throws IOException {
        mediaPlayer.stop();
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sign-in.fxml"));
        primaryStage.setTitle("Welcome to podcastio");
        primaryStage.setScene(new Scene(root, 800, 570));
        primaryStage.show();
    }
}
