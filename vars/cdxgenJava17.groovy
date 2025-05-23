// vars/cdxgenJava17.groovy
// def call(Map args = [:], Closure body) {
//   // считываем шаблон из resources
//   String yaml = libraryResource('cdxgen-java17.yaml')
//   podTemplate(yaml: yaml) {
//     body()
//   }
// }


def call() {
  return libraryResource('cdxgen-java17.yaml')
}
