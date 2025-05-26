def call() {
  return """
apiVersion: v1
kind: Pod
spec:  
  securityContext:
    fsGroup: 1000  
    runAsUser: 1000
    runAsGroup: 1000
  containers:
    - name: syft 
      image: xoroma/syft:test-a
      resources:
        limits:
          memory: "4Gi" 
          cpu: "3000m"
        requests:
          memory: "2Gi" 
          cpu: "2000m"                                  
      command:
        - cat
      tty: true
      volumeMounts:
        - name: syft-data
          mountPath: /src
  volumes:
    - name: syft-data
      persistentVolumeClaim:
        claimName: longhorn-jenkins-pv-claim  
  """
}