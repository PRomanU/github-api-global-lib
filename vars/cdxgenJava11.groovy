// // vars/cdxgenJava17.groovy
// def call(Map args = [:], Closure body) {
//   // считываем шаблон из resources
//   String yaml = libraryResource('cdxgen-java11.yaml')
//   podTemplate(yaml: yaml) {
//     body()
//   }
// }


def call() {
  return """
apiVersion: v1
kind: Pod
metadata:
  name: cdxgen-java11
spec:
  restartPolicy: Never
  containers:
    - name: cdxgen-java11
      image: ghcr.io/appthreat/cdxgen-java11:v11
      command:
        - cat
      tty: true
      env:
        - name: CDXGEN_DEBUG_MODE
          value: "debug"
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
        - name: cdxgen-data
          mountPath: /tmp
          subPath: data11/tmp
        - name: cdxgen-data
          mountPath: /root/.gradle
          subPath: data11/gradle
        - name: cdxgen-data
          mountPath: /opt/android-sdk-linux/platforms
          subPath: data11/opt/android-sdk-linux/platforms
        - name: cdxgen-data
          mountPath: /opt/android-sdk-linux/build-tools
          subPath: data11/opt/android-sdk-linux/build-tools
        - name: cdxgen-data
          mountPath: /opt/android-sdk-linux/ndk
          subPath: data11/opt/android-sdk-linux/ndk
        - name: cdxgen-data
          mountPath: /opt/android-sdk-linux/platform-tools
          subPath: data11/opt/android-sdk-linux/platform-tools
        - name: cdxgen-data
          mountPath: /root/.m2
          subPath: data11/m2
  volumes:
    - name: jenkins-pvc
      persistentVolumeClaim:
        claimName: longhorn-jenkins-pv-claim
    - name: cdxgen-data
      persistentVolumeClaim:
        claimName: cdxgen-pvc  
  """
}