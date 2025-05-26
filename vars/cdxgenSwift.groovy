def call() {
  return """
apiVersion: v1
kind: Pod
metadata:
  name: cdxgen-swift
spec:
  restartPolicy: Never
  containers:
    - name: cdxgen-swift
      image: ghcr.io/cyclonedx/cdxgen-debian-swift:v11
      command:
        - cat
      tty: true
      env:
        - name: CDXGEN_DEBUG_MODE
          value: "verbose"
        - name: WS_NAME
          value: "${WS_NAME}"
      resources:
        requests:
          cpu: "500m"
          memory: "1Gi"
          ephemeral-storage: "2Gi"
        limits:
          cpu: "2000m"
          memory: "2Gi"
          ephemeral-storage: "6Gi"
      volumeMounts:
        - name: jenkins-pvc
          mountPath: /app
  volumes:
    - name: jenkins-pvc
      persistentVolumeClaim:
        claimName: longhorn-jenkins-pv-claim  
  """
}