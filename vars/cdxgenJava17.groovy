// def call() {
//   return libraryResource('cdxgen-java17.yaml')
// }

def call() {
  return """
apiVersion: v1
kind: Pod
metadata:
  name: cdxgen-java17
spec:
  restartPolicy: Never
  containers:
    - name: cdxgen-java17
      image: ghcr.io/appthreat/cdxgen-java17:v11
      command:
        - cat
      tty: true
      env:
        - name: CDXGEN_DEBUG_MODE
          value: "debug"
        - name: WS_NAME
          value: "\${WS_NAME}"
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
        - name: cdxgen-data
          mountPath: /tmp
          subPath: data17/tmp
        - name: cdxgen-data
          mountPath: /root/.gradle
          subPath: data17/gradle
        - name: cdxgen-data
          mountPath: /opt/android-sdk-linux/platforms
          subPath: data17/opt/android-sdk-linux/platforms
        - name: cdxgen-data
          mountPath: /opt/android-sdk-linux/build-tools
          subPath: data17/opt/android-sdk-linux/build-tools
        - name: cdxgen-data
          mountPath: /opt/android-sdk-linux/ndk
          subPath: data17/opt/android-sdk-linux/ndk
        - name: cdxgen-data
          mountPath: /opt/android-sdk-linux/platform-tools
          subPath: data17/opt/android-sdk-linux/platform-tools
        - name: cdxgen-data
          mountPath: /root/.m2
          subPath: data17/m2
  volumes:
    - name: jenkins-pvc
      persistentVolumeClaim:
        claimName: longhorn-jenkins-pv-claim
    - name: cdxgen-data
      persistentVolumeClaim:
        claimName: cdxgen-pvc
"""
}
