package com.company;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Adriano Alves
 * Jun 04 2017
 * Demo of JavaFX using 3D animation
 */

public class DemoFX3DAnimation extends Application
{
    private Translate zAxisTrans;
    private double zPos = -60;
    private Button btRotateCamera, btRotateBox , btRotateCylinder;
    private Box box;
    private Cylinder cylinder;
    private PerspectiveCamera camera;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("3D Animation FX");
        // using FlowPane as root node with gap : 10
        FlowPane root = new FlowPane(10,10);
        // center the nodes
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root,380,400);
        primaryStage.setScene(scene);

        // init the buttons
        btRotateCamera = new Button("Rotate Camera");
        btRotateBox = new Button("Rotate Box");
        btRotateCylinder = new Button("Rotate Cylinder");

        // camera translation
        zAxisTrans = new Translate(0,0,zPos);

        // init the camera
        camera = new PerspectiveCamera(true);
        camera.setRotationAxis(Rotate.Y_AXIS);
        camera.getTransforms().addAll(zAxisTrans);
        camera.setFieldOfView(45);
        camera.setFarClip(120);
        //init the box
        box = new Box(10,20,30);
        box.setMaterial(new PhongMaterial(Color.DARKGREEN));
        box.setRotationAxis(Rotate.Y_AXIS);
        // init the cylinder
        cylinder = new Cylinder(5,35);
        cylinder.setMaterial(new PhongMaterial(Color.FUCHSIA));
        cylinder.setRotationAxis(Rotate.Y_AXIS);

        /*
         * Cylinder Animation
         * rotate the cylinder 90 degrees horizontal
         * Translate the cylinder 10 units down (1/2 of the box size)
         */
        cylinder.getTransforms().add(new Rotate(90,0,0));
//        cylinder.getTransforms().addAll(new Translate(10,0,0));

        // group to hold the box and the cylinder
        Group shapesGroup = new Group();
        shapesGroup.getChildren().addAll(box,cylinder);

        // Sub scene to handle the group with depth buffer enable
        SubScene shapesSub = new SubScene(shapesGroup,340,340,true, SceneAntialiasing.DISABLED);
        shapesSub.setCamera(camera);
        shapesSub.setFill(Color.BLACK);

        RotateTransition rotateCamera = new RotateTransition(new Duration(2000), camera);
        rotateCamera.setCycleCount(2);
        rotateCamera.setAutoReverse(true);
        rotateCamera.setByAngle(360);

        RotateTransition rotateBox = new RotateTransition(new Duration(2000), box);
        rotateBox.setCycleCount(2);
        rotateBox.setAutoReverse(true);
        rotateBox.setByAngle(360);

        RotateTransition rotateCylinder = new RotateTransition(new Duration(2000),cylinder);
        rotateCylinder.setCycleCount(2);
        rotateCylinder.setAutoReverse(true);
        rotateCylinder.setByAngle(360);

        btRotateCamera.setOnAction( e -> rotateCamera.play());
        btRotateBox.setOnAction( e -> rotateBox.play());
        btRotateCylinder.setOnAction( e -> rotateCylinder.play());

        root.getChildren().addAll(shapesSub,btRotateCamera,btRotateBox,btRotateCylinder);

        primaryStage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
