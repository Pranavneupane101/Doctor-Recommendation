package com.doctor.Client;


import com.gRPC.doctorRecommendation.ListResponse;
import com.gRPC.doctorRecommendation.UserResponse;
import com.gRPC.doctorRecommendation.UserServiceGrpc;
import com.gRPC.doctorRecommendation.UsersRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {


        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext()
                .build();
        UserServiceGrpc.UserServiceStub stub = UserServiceGrpc.newStub(channel);



            StreamObserver<ListResponse> onReceiving = new StreamObserver<ListResponse>() {
                @Override
                public void onNext(ListResponse value) {
                    System.out.println("The doctors are ");

                    List<UserResponse> responses = value.getResponseList();
                    for (UserResponse r : responses) {

                        System.out.println(r.getDesignation());
                        System.out.println(r.getDoctorName());
                        System.out.println(r.getExperience());
                        System.out.println(r.getDistance());
                        System.out.println(r.getAppointmentsList());
                        System.out.println(r.getAvailableFrom());
                        System.out.println(r.getAvailableTo());
                        System.out.println(r.getPhoneNo());
                        System.out.println(r.getDate());
                    }
                }

                @Override
                public void onError(Throwable t) {
                }

                @Override
                public void onCompleted() {
                }

            };


        StreamObserver<UsersRequest> onRequest = stub.getDoctorLiveLocation(onReceiving);
        Scanner sc = new Scanner(System.in);

        while (true){

            System.out.println("Enter the latitude");
            double lat = sc.nextDouble();
            System.out.println("Enter the longitude");
            double lon = sc.nextDouble();
            if(lat>100){
                System.out.println("Area Unreachable");
                break;
            }
            UsersRequest request=UsersRequest
                    .newBuilder()
                    .setName("Pranav")
                    .setDoctorType("CARDIOLOGIST")
                    .setLatitude(lat)
                    .setLongitude(lon)
                    .build()
                    ;
            onRequest.onNext(
               request
            );

        }
        onRequest.onCompleted();









    }










}
