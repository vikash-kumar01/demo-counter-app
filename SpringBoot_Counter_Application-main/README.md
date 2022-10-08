# STEP 1: Scanning Images using SonarQube
![image](https://user-images.githubusercontent.com/35370115/178953579-418e993d-7c71-44ca-af10-ce3c0b544dc6.png)

# GOD LEVEL CONFIGURATION FOR ME (SonarQube Config)

![image](https://user-images.githubusercontent.com/35370115/178953867-c697acc8-55d4-4f18-9bf2-aea48f132ac3.png)

# OUTPUT
![image](https://user-images.githubusercontent.com/35370115/178954153-c3840f46-fcd7-4028-aff4-d522e633ebfe.png)


# STEP 2: Creating Kubernetes Cluster

![image](https://user-images.githubusercontent.com/35370115/178930454-b4dc9207-ae0a-421e-b60b-3c7b9328aa50.png)

# STEP 3: Application Deploymemt onto Kubernetes Cluster

![image](https://user-images.githubusercontent.com/35370115/178943720-d57d0dbf-21f8-4d17-b227-5ca6798e8216.png)


# SpringBoot_Counter_Application
 
 
 ### 1.	Create a spring boot app that prints “hello-world-$counter” ($counter should increment on every refresh)
 
          1. SpringBoot_Counter_Application generates jar named Uber.jar, same jar has been used in Dockerfile to build & deploy images. 
          2. applications.properties contains the new port :9090 

 
 ### 2. Create an Uber jar for the spring boot app and execute your jar from the command line.
 
         1. mvn install              # Maven takes my springboot application and generated aftifacts(jar file inside target directory named Uber.jar ), 
         2. java -jar Uber.jar       # Executing the jar file
 
 
 ### 3.	Create Docker image for the spring boot app and run it as Docker container exporting the URL port (9090).
 
 
        docker build -t springbootapp .                                           #Build Docker Image 
        
### STEP 1 >> Building Docker Image from Docker file
    
    docker image build -t sringbootapp .
    docker image tag sringbootapp vikashashoke/sringbootapp:1.0             # Version 1.0 (Optionl for version maintaining i did & pushed to dockerHUb for Kubernetes)
    docker image tag sringbootapp vikashashoke/sringbootapp:latest          # Version 1.0 Latest version
    
    
### STEP 2 >> Pushing Docker Images to DockerHub

    docker image push vikashashoke/sringbootapp:1.0          # Docker login needed before pushing the image
    docker image push vikashashoke/sringbootapp:latest   



### Running docker container with exposing port 9090

     docker run -td --name springbootapp -p 9090:9090 springbootapp:latest    #Detached mode
     
![image](https://user-images.githubusercontent.com/35370115/163572637-2004e425-29b3-470c-836d-a6e5c0541e8e.png)
        
      
        
### 4.	Create a deployment YAML file for Kubernetes and deploy it on Minikube.


### Deployment.yml
   
      kind: Deployment
      apiVersion: apps/v1
      metadata:
         name: mydeployment
      spec:
         replicas: 2
         selector:     
            matchLabels:
               app: myspringbootapp
         template:
             metadata:
               labels:
                 app: myspringbootapp
             spec:
              containers:
               - name: myspringbootapp
                 image: vikashashoke/springbootapp:latest              # Docker Image we pushed to dockerHub now using here the same..
                 imagePullPolicy: Always
                 ports:
                 - containerPort: 9090
                 
### Step 1 >> Running deployment.yml on minikube
   
      kubectl apply -f deployment.yml
      
      kubectl get deploy  mydeployment      
      
### service.yml  (Optional Just for testing not mentioned in test)

     apiVersion: v1
     kind: Service
     metadata:
        name: my-nodeport-service
    spec:
      selector:
        app: myspringbootapp
      type: NodePort
      ports:
       - name: http
         port: 9090
         targetPort: 9090
         nodePort: 30036
         protocol: TCP

### Step 2 >> Exposing pods using service.yml (nodeport)


      kubectl apply -f service.yml
     
             
             
 
![image](https://user-images.githubusercontent.com/35370115/163572270-723029d1-7653-4747-9913-af2a1ca48f02.png)
