cd infra

#MongoDB for hospital-users

kubectl apply -f mongo-secrets.yml
kubectl apply -f mongo-init.yml
kubectl apply -f mongo-service.yml
kubectl apply -f mongo-stateful.yml

#Hospital users microservice

kubectl apply -f configmap.yml
kubectl apply -f service.yml
kubectl apply -f deployment.yml
kubectl apply -f service-account.yml