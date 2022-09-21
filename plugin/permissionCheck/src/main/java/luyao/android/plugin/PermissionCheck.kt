package luyao.android.plugin

import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.component.ArtifactType
import java.io.File

/**
 * Description:
 * Author: luyao
 * Date: 2022/9/6 15:32
 */
class PermissionCheck : Plugin<Project> {

    override fun apply(project: Project) {

        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->

            val manifestCheck =
                project.tasks.register(
                    "${variant.name}ManifestCheck",
                    ManifestCheckTask::class.java
                )

            variant.artifacts.use(manifestCheck)
                .wiredWithFiles(
                    ManifestCheckTask::mergedManifest,
                    ManifestCheckTask::updatedManifest
                )
                .toTransform(com.android.build.api.artifact.SingleArtifact.MERGED_MANIFEST)


        }
    }
}
